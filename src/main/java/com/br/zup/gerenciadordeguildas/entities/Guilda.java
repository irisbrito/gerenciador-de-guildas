package com.br.zup.gerenciadordeguildas.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "guildas")
public class Guilda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private String objetivos;
    private String linkDoChat;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guilda", targetEntity = Membro.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Membro> membros;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guilda", targetEntity = Atividade.class, cascade = CascadeType.ALL)
    private List<Atividade> atividades;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guilda", targetEntity = Ata.class, cascade = CascadeType.ALL)
    private List<Ata> atas;

}
