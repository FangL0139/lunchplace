package com.gds.lunchPlaceBackend.validator;

import com.gds.lunchPlaceBackend.configuration.APIException;
import com.gds.lunchPlaceBackend.dto.request.AddPlaceRequest;
import com.gds.lunchPlaceBackend.repo.LunchPlaceRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class InsertValidator {

    @Resource
    private LunchPlaceRepository repository;

    public void checkEmptyRequest(AddPlaceRequest request) throws APIException {
        if (isInputEmpty(request.placeName()) || isInputEmpty(request.postcode())) {
            throw new APIException("Place Information incomplete.", "ERR-01");
        }
    }

    private boolean isInputEmpty(String input) {
        return input.isEmpty() || input.isBlank();
    }

    public void isPlaceExist(AddPlaceRequest request) throws APIException {
        if (repository.findByPlaceName(request.placeName()).isPresent()) {
            throw new APIException("Lunch Place Existed.", "ERR-02");
        }
    }
}
