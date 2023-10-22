package com.gds.lunchPlaceBackend.service;

import com.gds.lunchPlaceBackend.configuration.APIException;
import com.gds.lunchPlaceBackend.dto.request.AddPlaceRequest;
import com.gds.lunchPlaceBackend.dto.response.GeneralResponse;
import com.gds.lunchPlaceBackend.dto.response.LunchPlaceResponse;
import com.gds.lunchPlaceBackend.entity.LunchPlace;
import com.gds.lunchPlaceBackend.repo.LunchPlaceRepository;
import com.gds.lunchPlaceBackend.validator.InsertValidator;
import jakarta.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LunchPlaceServiceImpl implements LunchPlaceService {
    private static final Logger logger = LoggerFactory.getLogger(LunchPlaceServiceImpl.class);

    private final LunchPlaceRepository repository;

    private final InsertValidator insertValidator;

    @Autowired
    public LunchPlaceServiceImpl(LunchPlaceRepository repository, InsertValidator insertValidator) {
        this.repository = repository;
        this.insertValidator = insertValidator;
    }

    @Override
    public LunchPlaceResponse saveLunchPlace(AddPlaceRequest request) throws APIException {
        try {
            insertValidator.checkEmptyRequest(request);
            logger.info(request.toString());
            return new LunchPlaceResponse(repository.saveAndFlush(LunchPlace.builder().placeName(request.placeName()).build()).getPlaceName());
        } catch (DataAccessException | PersistenceException e) {
            logger.error(e.getMessage());
            throw new APIException("Internal System Error.", "ERR-04");
        }
    }

    @Override
    public LunchPlaceResponse getRandomLunchPlace() throws APIException {
        try {
            return new LunchPlaceResponse(repository.findRandomPlace()
                    .orElseThrow(() -> new APIException("No place submitted.", "ERR-03")).getPlaceName());
        } catch (DataAccessException | PersistenceException e) {
            logger.error(e.getMessage());
            throw new APIException("Internal System Error.", "ERR-04");
        }
    }

    @Override
    public GeneralResponse deleteAllPlaces() throws APIException {
        try {
            repository.deleteAll();
            return GeneralResponse.builder().message("All places deleted.").build();
        } catch (DataAccessException | PersistenceException e) {
            logger.error(e.getMessage());
            throw new APIException("Internal System Error.", "ERR-04");
        }

    }
}
