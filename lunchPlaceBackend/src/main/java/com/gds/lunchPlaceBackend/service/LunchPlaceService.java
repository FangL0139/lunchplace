package com.gds.lunchPlaceBackend.service;

import com.gds.lunchPlaceBackend.configuration.APIException;
import com.gds.lunchPlaceBackend.dto.request.AddPlaceRequest;
import com.gds.lunchPlaceBackend.dto.response.GeneralResponse;
import com.gds.lunchPlaceBackend.dto.response.LunchPlaceResponse;

public interface LunchPlaceService {
    LunchPlaceResponse saveLunchPlace(AddPlaceRequest request) throws APIException;

    LunchPlaceResponse getRandomLunchPlace() throws APIException;

    GeneralResponse deleteAllPlaces() throws APIException;
}
