package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.services.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("atividades/")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Atividade adicionarAtividade(@RequestBody Atividade atividade){
        return atividadeService.cadastrarAtividade(atividade);
    }
}
