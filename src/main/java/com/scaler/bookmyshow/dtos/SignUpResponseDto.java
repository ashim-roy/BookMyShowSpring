package com.scaler.bookmyshow.dtos;

import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private ResponseStatus responseStatus;
    private Long userId;
}
