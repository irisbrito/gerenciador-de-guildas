package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.guilda.AtualizarGuildaDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.guilda.GuildaDTO;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.services.GuildaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("guildas/")
public class GuildaController {

    private GuildaService guildaService;
    private ModelMapper modelMapper;

    public GuildaController(GuildaService guildaService, ModelMapper modelMapper) {
        this.guildaService = guildaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuildaDTO cadastrarGuilda(@RequestBody @Valid GuildaDTO guildaDTO){
        Guilda guilda = modelMapper.map(guildaDTO, Guilda.class);
        guilda = guildaService.cadastrarGuilda(guilda);
        return  modelMapper.map(guilda, GuildaDTO.class);
    }

    @GetMapping
    public Iterable<Guilda> listarGuildas(){
        return guildaService.retornarTodasAsGuildas();
    }

    @GetMapping("{nome}/")
    public Guilda buscarGuildaPeloNome(@PathVariable String nome){
        return guildaService.buscarGuildaPeloNome(nome);
    }

    @PutMapping("{id}/")
    public Guilda atualizarGuilda(@PathVariable Integer id, @RequestBody AtualizarGuildaDTO guildaDTO){
        Guilda guilda = guildaService.atualizarGuilda(guildaDTO.converterDTOParaModel(id));
        return guilda;
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarGuilda(@PathVariable Integer id){
        guildaService.deletarGuilda(id);
    }
}
