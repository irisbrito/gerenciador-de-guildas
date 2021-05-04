package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.AtividadeDTO;
import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.services.AtividadeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("atividades/")
public class AtividadeController {

    private AtividadeService atividadeService;
    private ModelMapper modelMapper;

    public AtividadeController(AtividadeService atividadeService, ModelMapper modelMapper) {
        this.atividadeService = atividadeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtividadeDTO adicionarAtividade(@RequestBody @Valid AtividadeDTO atividadeDTO){
        Atividade atividade = modelMapper.map(atividadeDTO, Atividade.class);
        atividade = atividadeService.cadastrarAtividade(atividade);

        return modelMapper.map(atividade, AtividadeDTO.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Atividade> buscarAtividades(){
        return atividadeService.buscarAtividades();
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarAtividade(@PathVariable Integer id){
        atividadeService.deletarAtividade(id);
    }
}
