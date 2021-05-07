package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.atividade.CadastroAtividadeDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.atividade.AtualizacaoParcialAtividadeDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.atividade.AtualizarAtividadeDTO;
import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.services.AtividadeService;
import org.modelmapper.ModelMapper;
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
    public CadastroAtividadeDTO adicionarAtividade(@RequestBody @Valid CadastroAtividadeDTO atividadeDTO){
        Atividade atividade = modelMapper.map(atividadeDTO, Atividade.class);
        atividade = atividadeService.cadastrarAtividade(atividade);

        return modelMapper.map(atividade, CadastroAtividadeDTO.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Atividade> buscarAtividades(){
        return atividadeService.buscarAtividades();
    }

    @PutMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public Atividade atualizarAtividade(@PathVariable Integer id, @RequestBody AtualizarAtividadeDTO atividadeDTO){
        Atividade atividade = atividadeService.atualizarAtividade(atividadeDTO.converterDTOParaModel(id));

        return atividade;
    }

    @PatchMapping("{id}/")
    public Atividade atualizarAtividadeParcial(@PathVariable int id,
                                               @RequestBody @Valid AtualizacaoParcialAtividadeDTO atividadeDTO){
        Atividade atividade = atividadeDTO.converterDTOParaModel(id);
        return atividadeService.atualizarParcialAtividade(atividade);
    }

    @GetMapping("{guilda}/")
    @ResponseStatus(HttpStatus.OK)
    public CadastroAtividadeDTO buscarAtividadePelaGuilda(@PathVariable String guilda) {
        Atividade atividade = atividadeService.buscarAtividadePelaGuilda(guilda);
        return modelMapper.map(atividade, CadastroAtividadeDTO.class);
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarAtividade(@PathVariable Integer id){
        atividadeService.deletarAtividade(id);
    }
}
