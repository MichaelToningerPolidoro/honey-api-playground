package com.honeyautomation.apiplayground.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nickName;
    private String name;
    private String email;

    @OneToOne(targetEntity = Password.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "password_fk", referencedColumnName = "id")
    private Password password;

    @ManyToOne(targetEntity = ProgrammingTimeOption.class, cascade = CascadeType.DETACH)
    @JoinColumn(name = "programming_time_opt_fk", referencedColumnName = "id")
    private ProgrammingTimeOption programmingTimeOption;

    @OneToOne(targetEntity = BornData.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "born_data_fk", referencedColumnName = "id")
    private BornData bornData;

    @ManyToMany(targetEntity = Hobby.class, cascade = CascadeType.DETACH)
    @Fetch(FetchMode.JOIN)
    private List<Hobby> hobbies;
}
