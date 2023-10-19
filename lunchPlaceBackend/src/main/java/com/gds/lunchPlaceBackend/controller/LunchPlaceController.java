package com.gds.lunchPlaceBackend.controller;

import com.gds.lunchPlaceBackend.configuration.APIException;
import com.gds.lunchPlaceBackend.dto.request.AddPlaceRequest;
import com.gds.lunchPlaceBackend.entity.LunchPlace;
import com.gds.lunchPlaceBackend.service.LunchPlaceService;
import com.gds.lunchPlaceBackend.validator.InsertValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lunch-places")
public class LunchPlaceController {
    @Autowired
    private LunchPlaceService service;

    @Autowired
    private InsertValidator validator;

    @PostMapping
    public ResponseEntity<LunchPlace> createLunchPlace(@RequestBody AddPlaceRequest addPlaceRequest) throws APIException {
        validator.isPlaceExist(addPlaceRequest);
        return ResponseEntity.ok().body(service.saveLunchPlace(
                LunchPlace.builder().placeName(addPlaceRequest.placeName())
                        .postcode(addPlaceRequest.postcode()).build()));
    }

    @GetMapping("/random")
    public LunchPlace getRandomLunchPlace() throws APIException {
        return service.getRandomLunchPlace();
    }
}
