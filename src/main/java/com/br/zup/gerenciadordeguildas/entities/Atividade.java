package com.br.zup.gerenciadordeguildas.entities;

import com.br.zup.gerenciadordeguildas.enums.Status;
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
    private List<String> responsaveis;
    private Status status;

    @ManyToOne
    private Guilda guilda;
}
