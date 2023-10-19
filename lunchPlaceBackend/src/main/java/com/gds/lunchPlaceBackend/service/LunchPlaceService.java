package com.gds.lunchPlaceBackend.service;

import com.gds.lunchPlaceBackend.configuration.APIException;
import com.gds.lunchPlaceBackend.entity.LunchPlace;
import com.gds.lunchPlaceBackend.repo.LunchPlaceRepository;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LunchPlaceService {
    private static final Logger logger = LoggerFactory.getLogger(LunchPlaceService.class);
    @Resource
    private LunchPlaceRepository repository;


    public LunchPlace saveLunchPlace(LunchPlace lunchPlace) {
        return repository.saveAndFlush(lunchPlace);
    }

    public LunchPlace getRandomLunchPlace() throws APIException {
        return repository.findRandomPlace().orElseThrow(() -> new APIException("No place submitted.", "ERR-03"));
    }
}
