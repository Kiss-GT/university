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
public class Person {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy ="person")
    private List<Drink>drinks;

    public Person(Long id, String name) {
        Id = id;
        this.name = name;
    }
}
