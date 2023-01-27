package com.honeyautomation.apiplayground.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    @OneToOne(targetEntity = Password.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "password_fk", referencedColumnName = "id")
    private Password password;

    @OneToOne(targetEntity = ProgrammingTimeOption.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "programming_time_opt_fk", referencedColumnName = "id")
    private ProgrammingTimeOption programmingTimeOption;


    // FIXME valor derivado de outra tablea e será um objeto (entidade) do tipo BornData
    //  provavelemente pegar valor igual o ID gerado do usuario e então criar um objeto
    //  PROVAVELMENTE É DO TIPO BORN DATA
    //@Column(name = "born_data_id")
    @OneToOne(targetEntity = BornData.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "born_data_fk", referencedColumnName = "id")
    private BornData bornDataId;


    // FIXME valores derivados de outra tabela, um usuário para muitos hobbies, talvez seja List<Integer>
    //  rever o nome dessa coluna !!!!!!!!!!!!!!!!!!! ACHO QUE NÃO TEM ESSA COLUNA, POIS O RELACIONAMENTO
    //  É REALIZADO EM UMA OUTRA TABELA CRIADA PELO HIBERNATE
    //  um para muitos
    //@Column(name = "hobbies_ids")
    @ManyToMany(targetEntity = Hobby.class, cascade = CascadeType.ALL)
    private List<Hobby> hobbies;

}
