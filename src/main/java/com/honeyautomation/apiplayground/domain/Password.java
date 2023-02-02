package com.honeyautomation.apiplayground.domain;

import com.honeyautomation.apiplayground.encoder.DefaultPasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// it is only for learning purpose
@Table(name = "tb_passwords")
public class Password {

    // foreign key a senha, lembrando que essa senha tem o mesmo ID do usuário criado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String password;

    public Password(String rawPassword) {
        password = DefaultPasswordEncoder.getDefaultEncoder().encode(rawPassword);
    }
}
