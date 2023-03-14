package com.honeyautomation.apiplayground.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_born_data")
public class BornData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private LocalDate date;

    @OneToOne(targetEntity = Country.class, cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false, name = "country_fk", referencedColumnName = "id")
    private Country country;

    public BornData(LocalDate date, Country country) {
        this.date = date;
        this.country = country;
    }
}
