package com.honeyautomation.apiplayground.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_born_data")
public class BornData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToOne(targetEntity = Country.class, cascade = CascadeType.DETACH)
    @JoinColumn(name = "country_fk", referencedColumnName = "id")
    private Country country;

    public BornData(Date date, Country country) {
        this.date = date;
        this.country = country;
    }
}
