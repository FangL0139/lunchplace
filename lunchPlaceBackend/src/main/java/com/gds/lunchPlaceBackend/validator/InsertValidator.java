package com.gds.lunchPlaceBackend.validator;

import com.gds.lunchPlaceBackend.configuration.APIException;
import com.gds.lunchPlaceBackend.dto.request.AddPlaceRequest;
import com.gds.lunchPlaceBackend.repo.LunchPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class InsertValidator {

    @Autowired
    private LunchPlaceRepository repository;

    public void checkEmptyRequest(AddPlaceRequest request) throws APIException {
        if (isInputEmpty(request.placeName())) {
            throw new APIException("Place Information incomplete.", "ERR-01");
        }
    }

    private boolean isInputEmpty(String input) {
        return isNull(input) || input.isEmpty() || input.isBlank();
    }

}
