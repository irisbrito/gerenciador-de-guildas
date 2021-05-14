package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.atividade.AtualizarParcialAtividadeDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.atividade.CadastroAtividadeDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.atividade.AtualizarAtividadeDTO;
import com.br.zup.gerenciadordeguildas.dtos.saida.atividade.AtualizarAtividadeDTOSaida;
import com.br.zup.gerenciadordeguildas.dtos.saida.atividade.CadastroAtividadeDTOSaida;
import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.services.AtividadeService;
import com.br.zup.gerenciadordeguildas.services.GuildaService;
import com.br.zup.gerenciadordeguildas.services.MembroService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("atividades/")
public class AtividadeController {

    private AtividadeService atividadeService;
    private GuildaService guildaService;
    private MembroService membroService;
    private ModelMapper modelMapper;

    public AtividadeController(AtividadeService atividadeService, GuildaService guildaService, MembroService membroService, ModelMapper modelMapper) {
        this.atividadeService = atividadeService;
        this.guildaService = guildaService;
        this.membroService = membroService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadastroAtividadeDTOSaida cadastrarAtividade(@RequestBody @Valid CadastroAtividadeDTO cadastroAtividadeDTO){
        Guilda guilda = guildaService.buscarGuildaPeloNome(cadastroAtividadeDTO.getGuilda());
        List<Membro> membros = new ArrayList<>();
        cadastroAtividadeDTO.getResponsaveis()
                .forEach( nomeDoMembro -> membros.add(membroService.buscarMembroPeloNome(nomeDoMembro)));
        Atividade atividade = atividadeService.cadastrarAtividade(cadastroAtividadeDTO.converterDTOparaEntity(guilda, membros));

        return CadastroAtividadeDTOSaida.converterEntityParaDTOSaida(atividade);
    }

    @PostMapping("{id}/representantes/{idDoMembro}/")
    @ResponseStatus(HttpStatus.CREATED)
    public CadastroAtividadeDTOSaida cadastrarRepresentanteDaAtividade(@PathVariable Integer id, @PathVariable Integer idDoMembro){
        Atividade atividade = atividadeService.adicionarRepresentanteNaAtividade(id, idDoMembro);
        return CadastroAtividadeDTOSaida.converterEntityParaDTOSaida(atividade);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Atividade> buscarAtividades(){
        return atividadeService.buscarAtividades();
    }

    @GetMapping("{id}/")
    public Atividade buscarAtividadePeloId(@PathVariable Integer id){
        return atividadeService.buscarAtividadePeloId(id);
    }

    @GetMapping("/guilda/{guilda}/")
    @ResponseStatus(HttpStatus.OK)
    public CadastroAtividadeDTO buscarAtividadePelaGuilda(@PathVariable String guilda) {
        Atividade atividade = atividadeService.buscarAtividadePelaGuilda(guilda);
        return modelMapper.map(atividade, CadastroAtividadeDTO.class);
    }

    @PutMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public Atividade atualizarAtividade(@PathVariable Integer id, @RequestBody AtualizarAtividadeDTO atividadeDTO){
        Atividade atividade = atividadeService.atualizarAtividade(atividadeDTO.converterDTOParaEntity(id));

        return atividade;
    }

    @PatchMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public AtualizarAtividadeDTOSaida atualizarAtividadeParcial(@PathVariable int id,
                                                                @RequestBody @Valid AtualizarParcialAtividadeDTO atividadeDTO){
        Atividade atividade = atividadeDTO.converterDTOParaEntity(id);
        atividade = atividadeService.atualizarParcialAtividade(atividade);
        return modelMapper.map(atividade, AtualizarAtividadeDTOSaida.class);
    }

    @DeleteMapping("{idDaAtividade}/atividades/{idDoMembro}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarResponsavelDaAtividade(@PathVariable Integer idDaAtividade, @PathVariable Integer idDoMembro) {
        atividadeService.deletarResponsavelAtividade(idDaAtividade, idDoMembro);
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarAtividade(@PathVariable Integer id){
        atividadeService.deletarAtividade(id);
    }
}
