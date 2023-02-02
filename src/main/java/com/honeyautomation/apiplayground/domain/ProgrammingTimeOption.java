package com.honeyautomation.apiplayground.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_programming_time_options")
public class ProgrammingTimeOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "programming_time")
    private String programmingTime;

    public ProgrammingTimeOption(String programmingTime) {
        this.programmingTime = programmingTime;
    }
}
