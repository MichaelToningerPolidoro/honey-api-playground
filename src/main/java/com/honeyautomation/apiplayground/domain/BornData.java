package com.honeyautomation.apiplayground.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_born_data")
public class BornData {

    @Id
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToOne
    // here will be Country when implemented
    private Country country;
}
