package com.honeyautomation.apiplayground.domain;

import javax.persistence.*;

@Entity
@Table(name = "tb_countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private String iso;
}
