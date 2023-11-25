package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Actor extends BaseModel{
    private String name;

    //previous movie of this actor  etc..
}
