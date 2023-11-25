package com.scaler.bookmyshow.dtos;

import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/*
// will return the booking id, status, amount once I create booking object in db I will only
// return the booking id to client. Whenever required I will fetch from DB. From booking id
// I can get all details, status, show details, seat, payment. But I will not expose all the
// things directly, I will only give you booking id. Whatever you need fetch on your own.

 */
public class BookMovieResponseDto {    //IN THIS I WILL HAVE RESPONSE STATUS
    private ResponseStatus responseStatus;
    private int amount;
    private Long bookingId;
}
