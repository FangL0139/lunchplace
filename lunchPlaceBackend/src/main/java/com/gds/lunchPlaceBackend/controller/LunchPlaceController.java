package com.gds.lunchPlaceBackend.controller;

import com.gds.lunchPlaceBackend.entity.LunchPlace;
import com.gds.lunchPlaceBackend.service.LunchPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lunch-places")
public class LunchPlaceController {
    @Autowired
    private LunchPlaceService service;

    @PostMapping
    public LunchPlace createLunchPlace(@RequestBody LunchPlace lunchPlace) {
        return service.saveLunchPlace(lunchPlace);
    }

    @GetMapping("/random")
    public LunchPlace getRandomLunchPlace() {
        return service.getRandomLunchPlace();
    }
}
