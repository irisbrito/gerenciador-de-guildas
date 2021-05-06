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
    private String linkDoChat;

    @ManyToMany(mappedBy = "guildas")
    private List<Membro> membros;

    @OneToMany
    private List<Atividade> atividades;

    @OneToMany
    private List<Ata> atas;
}
