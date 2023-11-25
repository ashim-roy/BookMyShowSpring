package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{

    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;
}

/*

SHOWSeat    :    SHOW

for the mapping table/ class think about what kind of objects we are talking about, what kind of object showseat will be.
Show id-123, seat A1
Show id: 123 seat A2.
We can have 100 seats in 1 show.. for 1 show I will have 100 objects..
1 showseat object, in that 1 shows will be there.. but many showseat objects for 1 show.
1 show can have many seat, for every seat we will have many objects. So many toOne.


 */