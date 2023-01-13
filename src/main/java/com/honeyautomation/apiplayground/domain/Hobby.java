package com.honeyautomation.apiplayground.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_hobbies")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String hobby;
}
