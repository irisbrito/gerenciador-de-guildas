package com.br.zup.gerenciadordeguildas.dtos.entrada.guilda;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuildaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String nome;
    @NotNull
    @NotEmpty
    private String descricao;
    @NotNull
    private String objetivos;
    @NotNull
    private String linkDoChat;
    @NotNull
    private List<Membro> membros;
    private List<Atividade> atividades;
    private List<Ata> atas;
}
