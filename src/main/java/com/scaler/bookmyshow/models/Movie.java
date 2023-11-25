package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String name;

    @ManyToMany
    private List<Actor> actors;
    private String duration;
    private double rating;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Genre> genres;


}
