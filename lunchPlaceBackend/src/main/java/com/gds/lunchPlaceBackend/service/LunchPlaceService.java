package com.gds.lunchPlaceBackend.service;

import com.gds.lunchPlaceBackend.entity.LunchPlace;
import com.gds.lunchPlaceBackend.repo.LunchPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class LunchPlaceService {
    @Autowired
    private LunchPlaceRepository repository;

    public LunchPlace saveLunchPlace(LunchPlace lunchPlace) {
        return repository.save(lunchPlace);
    }

    public LunchPlace getRandomLunchPlace() {
        List<LunchPlace> lunchPlaces = (List<LunchPlace>) repository.findAll();
        if (!lunchPlaces.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(lunchPlaces.size());
            return lunchPlaces.get(randomIndex);
        }
        return null;
    }
}
