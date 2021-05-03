package com.br.zup.gerenciadordeguildas.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "guildas")
public class Guilda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private String objetivos;
    private String grupo;
    @OneToMany()
    private List<Representante> representantes;
    @OneToMany
    private List<Atividade> atividades;
    @OneToMany
    private Ata ata;



}
