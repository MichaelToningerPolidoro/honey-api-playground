package com.honeyautomation.apiplayground.domain;

import com.honeyautomation.apiplayground.constants.Validations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_born_data")
public class BornData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = Validations.MAX_LENGTH_BORN_DATA_DATE)
    private LocalDate date;

    @OneToOne(targetEntity = Country.class, cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false, name = "country_fk", referencedColumnName = "id")
    private Country country;

    public BornData(LocalDate date, Country country) {
        this.date = date;
        this.country = country;
    }
}
