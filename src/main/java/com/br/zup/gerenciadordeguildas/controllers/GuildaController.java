package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Representante;
import com.br.zup.gerenciadordeguildas.services.GuildaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("guildas")
public class GuildaController {

    private GuildaService guildaService;
    private ModelMapper modelMapper;

    public GuildaController(GuildaService guildaService, ModelMapper modelMapper) {
        this.guildaService = guildaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guilda cadastrarGuilda(@RequestBody Guilda guilda){
        return guildaService.cadastrarGuilda(guilda);
    }

    @GetMapping
    public List<Guilda> listarGuildas(){
        return guildaService.retornarTodasAsGuildas();
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarGuildas(@PathVariable Integer id){
        guildaService.deletarGuildas(id);
    }
}
