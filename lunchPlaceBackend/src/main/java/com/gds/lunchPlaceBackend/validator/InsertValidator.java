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
        if (isInputEmpty(request.placeName())) {
            throw new APIException("Place Information incomplete.", "ERR-01");
        }
    }

    private boolean isInputEmpty(String input) {
        return input.isEmpty() || input.isBlank();
    }

}
