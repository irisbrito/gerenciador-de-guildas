package com.br.zup.gerenciadordeguildas.entities;

import com.br.zup.gerenciadordeguildas.enums.Status;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "atividades")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "atividades_membro",
        joinColumns = @JoinColumn(name = "atividade_id"),
        inverseJoinColumns = @JoinColumn(name = "membro_id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private List<Membro> responsaveis;
    private Status status;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guilda_id")
    private Guilda guilda;
}
