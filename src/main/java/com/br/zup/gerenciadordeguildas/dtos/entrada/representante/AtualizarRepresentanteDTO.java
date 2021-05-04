package com.br.zup.gerenciadordeguildas.dtos.entrada.representante;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Representante;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class AtualizarRepresentanteDTO {

    @NotNull
    @Size(min = 2, max = 100, message = "Por favor digite um nome válido")
    private String nome;
    @Email(message = "Email inválido")
    private String email;
    @NotNull
    private String zenity;
    @NotNull
    private List<Guilda> guildas;

    public Representante converterDTOParaModel(Integer id){
        Representante representante = new Representante();
        representante.setId(id);
        representante.setNome(this.nome);
        representante.setEmail(this.email);
        representante.setZenity(this.zenity);
        representante.setGuildas(this.guildas);

        return representante;
    }
}
