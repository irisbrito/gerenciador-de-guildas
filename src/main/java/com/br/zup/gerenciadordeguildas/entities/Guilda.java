package com.br.zup.gerenciadordeguildas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String linkDoChat;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guilda", targetEntity = Membro.class)
    private List<Membro> membros;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guilda", targetEntity = Atividade.class)
    private List<Atividade> atividades;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guilda", targetEntity = Ata.class)
    private List<Ata> atas;


}
