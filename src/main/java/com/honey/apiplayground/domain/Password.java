package com.honey.apiplayground.domain;

import com.honey.apiplayground.constants.Validations;
import com.honey.apiplayground.encoder.DefaultPasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "tb_passwords")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = Validations.MAX_LENGTH_PASSWORD)
    private String password;

    public Password(String rawPassword) {
        password = DefaultPasswordEncoder.getDefaultEncoder().encode(rawPassword);
    }
}
