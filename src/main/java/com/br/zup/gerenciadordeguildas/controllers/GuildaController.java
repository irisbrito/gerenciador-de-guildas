package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.guilda.AtualizarParcialGuildaDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.guilda.GuildaDTO;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.services.GuildaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("{id}/atas/{idDaAta}/")
    @ResponseStatus(HttpStatus.CREATED)
    public Guilda cadastrarAtaNaGuilda(@PathVariable Integer id, @PathVariable Integer idDaAta){
        return guildaService.adicionarAtaNaGuilda(id, idDaAta);
    }

    @PostMapping("{id}/atividades/{idDaAtividade}/")
    @ResponseStatus(HttpStatus.CREATED)
    public Guilda cadastrarAtividadeNaGuilda(@PathVariable Integer id, @PathVariable Integer idDaAtividade) {
        return guildaService.adicionarAtividadeNaGuilda(id, idDaAtividade);
    }

    @GetMapping
    public Iterable<Guilda> listarGuildas(){
        return guildaService.retornarTodasAsGuildas();
    }

    @GetMapping("{nome}/")
    public Guilda buscarGuildaPeloNome(@PathVariable String nome){
        return guildaService.buscarGuildaPeloNome(nome);
    }

    @GetMapping("{id}/representantes/")
    public Iterable<Membro> buscaRepresentantesDaGuilda(@PathVariable Integer id){
        Guilda guilda = guildaService.buscarGuildaPeloId(id);
        return guildaService.buscarRepresentantesDaGuilda(guilda);
    }

    @PatchMapping("{id}/")
    public Guilda atualizarGuildaParcial(@PathVariable Integer id, @RequestBody AtualizarParcialGuildaDTO guildaDTO){
        return guildaService.atualizarGuildaParcial(guildaDTO.converterDTOParaModel(id));
    }

    @DeleteMapping("{idDaGuilda}/membros/{idDoMembro}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarMembroDaGuilda(@PathVariable Integer idDaGuilda, @PathVariable Integer idDoMembro){
        guildaService.deletarMembroDaGuilda(idDaGuilda, idDoMembro);
    }

    @DeleteMapping("{idDaGuilda}/atividades/{idDaAtividade}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarAtividadeDaGuilda(@PathVariable Integer idDaGuilda, @PathVariable Integer idDaAtividade){
        guildaService.deletarMembroDaGuilda(idDaGuilda, idDaAtividade);
    }

    @DeleteMapping("{idDaGuilda}/atas/{idDaAta}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarAtaDaGuilda(@PathVariable Integer idDaGuilda, @PathVariable Integer idDaAta){
        guildaService.deletarMembroDaGuilda(idDaGuilda, idDaAta);
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarGuilda(@PathVariable Integer id){
        guildaService.deletarGuilda(id);
    }
}
