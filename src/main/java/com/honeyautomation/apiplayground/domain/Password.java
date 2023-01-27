package com.honeyautomation.apiplayground.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
// it is only for learning purpose
@Table(name = "tb_passwords")
public class Password {

    // foreign key a senha, lembrando que essa senha tem o mesmo ID do usuário criado
    @Id
    private int id;
    private String password;
}