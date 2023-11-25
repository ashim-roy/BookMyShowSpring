package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dtos.BookMovieRequestDto;
import com.scaler.bookmyshow.dtos.BookMovieResponseDto;
import com.scaler.bookmyshow.models.Booking;
//import com.scaler.bookmyshow.models.BookingStatus;
import com.scaler.bookmyshow.models.ResponseStatus;
//import com.scaler.bookmyshow.models.User;
//import com.scaler.bookmyshow.repositories.UserRepository;
import com.scaler.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller // This tells SB that BookingController is a special class. and when you are
// initializing the spring application, you will have to create an object of it.
public class BookingController {
    private BookingService bookingService; // its a field

    //@Autowired //someone should call this constructor and place the object of booking service
    //inject the dependency
    /*
    how?  Below is constructor injection
BookingController(BookingService bookingService){  booking controller will take an object of booking service.
In the BookingController class, the BookingService dependency is injected through the constructor. This is a
common way to implement dependency injection, where the dependent class (BookingController in this case)
receives its dependencies through its constructor.
Constructor injectionhas several advantages, including making dependencies explicit,
allowing for easier testing (through the use of mock or fake implementations during testing),
and promoting a clear separation of concerns in your code.
    */
    @Autowired
    BookingController(BookingService bookingService){  // booking controller will take an object of booking service.
        this.bookingService = bookingService;
    }
    BookMovieResponseDto bookMovie(BookMovieRequestDto requestDto){
        BookMovieResponseDto responseDto = new BookMovieResponseDto();
        try {
            Booking booking = bookingService.bookMovie(requestDto.getUserId(),
                    requestDto.getShowId(),
                    requestDto.getShowSeatIDs());


            responseDto.setBookingId(booking.getId()); //assuming booking happened. payment happened, booking is persisted in DB.
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        } catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            throw new RuntimeException(e);
        }

        return responseDto;
    }
}
