package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String number;
    private int rowVal;
    private int ColVal;

    //one seat will be of one type. we create enum seatType
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
}
