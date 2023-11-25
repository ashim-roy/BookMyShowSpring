package com.scaler.bookmyshow.models;

public enum BookingStatus {
    CONFIRMED,  //1
    PENDING,     //2
    CANCELLED,    //3  it will have id. in the status column of booking table you will give id not
}

/*
starts with 1. If status is confirmed then,  In the status col of booking table you will give if 1
not the confirmed word. Int will 4 byte but  if you use string word confirmed it will take 9 byte..
every enum will have table in DB.
 */