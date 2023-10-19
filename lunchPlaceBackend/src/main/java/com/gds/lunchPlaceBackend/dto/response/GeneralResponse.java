package com.gds.lunchPlaceBackend.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralResponse {
    String errorCode;
    String message;
}
