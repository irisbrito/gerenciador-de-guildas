package com.br.zup.gerenciadordeguildas.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "membros")

public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String zenity;
    private boolean representante;

    @ManyToOne
    private Guilda guilda;

}
