package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity

public class Booking extends BaseModel{

    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    @OneToMany
    //list of showseat
    private List<ShowSeat> showSeat;
    //private Date bookedAt; can come from created at..

    //booking status is enum..
    @Enumerated(EnumType.ORDINAL)   // ordinal means we give only id
    private BookingStatus bookingStatus;

    private int amount;
    @OneToMany
    private List<Payment> payments;

}

/*
  1             M
booking     showseat   ==> 1 : M
  1               1

  one booking can have many showseats, and one showseat can be
  booked in one booking. we are not considering cancellation.

  but if we consider cancellation then M: M
 */

/*
    1           M
  Booking     Payment
     1           1
 */
