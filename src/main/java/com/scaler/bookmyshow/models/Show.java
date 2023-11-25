package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Show extends BaseModel{

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Screen screen;
    private Date startTime;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

}

/*
show: movie, theatre, screen, time... one show can run in one screen
1             1
SHOW    --> Movie ==> M : 1
  M            1

  1            1
  SHOW  -->  SCREEN  = M: 1
   M            1

   one screen can run multiple shows not at a time.
 */