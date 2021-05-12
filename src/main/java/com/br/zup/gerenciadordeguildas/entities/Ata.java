package com.br.zup.gerenciadordeguildas.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@Table(name = "atas")
public class Ata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Instant data;
    private String pauta;
    private String assuntos;

    @ManyToOne
    @JoinColumn(name = "guilda_id")
    private Guilda guilda;
}