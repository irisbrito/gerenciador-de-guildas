package com.br.zup.gerenciadordeguildas.entities;

import com.br.zup.gerenciadordeguildas.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "atividades")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;

    @ManyToMany
    @JoinTable(
        name = "atividades_membro",
        joinColumns = @JoinColumn(name = "atividade_id"),
        inverseJoinColumns = @JoinColumn(name = "membro_id")
    )
    private List<Membro> responsaveis;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "guilda_id")
    private Guilda guilda;
}
