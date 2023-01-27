package com.honeyautomation.apiplayground.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_born_data")
public class BornData {

    @Id
    private int id;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @OneToOne
    // here will be Country when implemented
    private int country;
}
