package com.gds.lunchPlaceBackend.controller;

import com.gds.lunchPlaceBackend.configuration.APIException;
import com.gds.lunchPlaceBackend.dto.request.AddPlaceRequest;
import com.gds.lunchPlaceBackend.dto.response.GeneralResponse;
import com.gds.lunchPlaceBackend.dto.response.LunchPlaceResponse;
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
    public ResponseEntity<LunchPlaceResponse> createLunchPlace(@RequestBody AddPlaceRequest addPlaceRequest) throws APIException {
        return ResponseEntity.ok().body(service.saveLunchPlace(
                LunchPlace.builder()
                        .placeName(addPlaceRequest.placeName())
                        .build()));
    }

    @GetMapping("/random")
    public ResponseEntity<LunchPlaceResponse> getRandomLunchPlace() throws APIException {
        return ResponseEntity.ok().body(service.getRandomLunchPlace());
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<GeneralResponse> deleteAllPlaces() throws APIException {
        return ResponseEntity.ok().body(service.deleteAllPlaces());
    }
}
