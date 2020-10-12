package com.kgt.university.drinks;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@ToString
@Entity
public class Drink {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;

    @ManyToOne
    private Person person;


    public Drink(Long id, String name) {
        Id = id;
        this.name = name;
    }
}
