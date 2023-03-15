package com.honeyautomation.apiplayground.domain;

import com.honeyautomation.apiplayground.constants.Validations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = Validations.MAX_LENGTH_COUNTRY)
    private String country;

    @Column(nullable = false, unique = true, length = Validations.MAX_LENGTH_COUNTRY_ISO)
    private String iso;
}
