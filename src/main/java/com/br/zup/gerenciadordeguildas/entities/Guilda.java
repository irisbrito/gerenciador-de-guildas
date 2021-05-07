package com.br.zup.gerenciadordeguildas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_guildas_membros",
    joinColumns = @JoinColumn(name = "guildas_id"),
    inverseJoinColumns = @JoinColumn(name = "membros_id"))
    private List<Membro> membros;

    @OneToMany
    private List<Atividade> atividades;

    @OneToMany
    private List<Ata> atas;
}
