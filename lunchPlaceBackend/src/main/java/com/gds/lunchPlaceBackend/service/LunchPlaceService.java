package com.gds.lunchPlaceBackend.service;

import com.gds.lunchPlaceBackend.configuration.APIException;
import com.gds.lunchPlaceBackend.dto.response.GeneralResponse;
import com.gds.lunchPlaceBackend.dto.response.LunchPlaceResponse;
import com.gds.lunchPlaceBackend.entity.LunchPlace;
import com.gds.lunchPlaceBackend.repo.LunchPlaceRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LunchPlaceService {
    private static final Logger logger = LoggerFactory.getLogger(LunchPlaceService.class);
    @Resource
    private LunchPlaceRepository repository;


    public LunchPlaceResponse saveLunchPlace(LunchPlace lunchPlace) throws APIException {
        try {
            return new LunchPlaceResponse(repository.saveAndFlush(lunchPlace).getPlaceName());
        }catch (PersistenceException e){
            logger.error(e.getMessage());
            throw new APIException("Internal System Error.","ERR-04");
        }
    }

    public LunchPlaceResponse getRandomLunchPlace() throws APIException {
        try{
            return new LunchPlaceResponse(repository.findRandomPlace()
                    .orElseThrow(() -> new APIException("No place submitted.", "ERR-03")).getPlaceName());
        }catch (PersistenceException e){
            logger.error(e.getMessage());
            throw new APIException("Internal System Error.","ERR-04");
        }
    }

    public GeneralResponse deleteAllPlaces() throws APIException {
        try{
            repository.deleteAll();
            return GeneralResponse.builder().message("All places deleted.").build();
        }catch (DataAccessException|PersistenceException e){
            logger.error(e.getMessage());
            throw new APIException("Internal System Error.","ERR-04");
        }

    }
}
