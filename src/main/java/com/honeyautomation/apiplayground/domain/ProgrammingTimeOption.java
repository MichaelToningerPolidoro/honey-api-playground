package com.honeyautomation.apiplayground.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_programming_time_options")
public class ProgrammingTimeOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "programming_time")
    private String programmingTime;
}
