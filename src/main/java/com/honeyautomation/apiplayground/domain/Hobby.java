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
@Table(name = "tb_hobbies")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = Validations.MAX_LENGTH_HOBBY_VALUE)
    private String hobby;
}
