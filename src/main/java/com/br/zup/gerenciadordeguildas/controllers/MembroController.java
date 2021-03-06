package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.membro.AtualizarMembroParcialDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.membro.CadastroMembroDTO;
import com.br.zup.gerenciadordeguildas.dtos.saida.membro.CadastroMembroDTOSaida;
import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.services.GuildaService;
import com.br.zup.gerenciadordeguildas.services.MembroService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("membros/")
public class MembroController {

    private MembroService membroService;
    private GuildaService guildaService;
    private ModelMapper modelMapper;

    public MembroController(MembroService membroService, GuildaService guildaService, ModelMapper modelMapper) {
        this.membroService = membroService;
        this.modelMapper = modelMapper;
        this.guildaService = guildaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadastroMembroDTOSaida cadastrarMembro(@RequestBody @Valid CadastroMembroDTO cadastroMembroDTO){
        Guilda guilda = guildaService.buscarGuildaPeloNome(cadastroMembroDTO.getGuilda());
        Membro membro = membroService.cadastrarMembro(cadastroMembroDTO.converterDTOparaEntity(guilda));

        return CadastroMembroDTOSaida.converterEntityParaDTOSaida(membro);
    }

    @GetMapping
    public Iterable<Membro> buscarMembros(){
        return membroService.buscarMembros();
    }

    @GetMapping("{id}/")
    public Membro buscarMembroPeloId(@PathVariable Integer id){
        return membroService.buscarMembroPeloId(id);
    }

    @GetMapping("{id}/atividades/")
    public List<Atividade> buscarAtividadesDeUmResponsavel(@PathVariable Integer id){
        return membroService.buscarAtividadesDeUmResponsavel(id);
    }

    @PatchMapping("{id}/")
    public AtualizarMembroParcialDTO atualizarMembroParcial(@PathVariable Integer id,
                                         @RequestBody @Valid AtualizarMembroParcialDTO membroDTO){
        Membro membroParaAtualizar = modelMapper.map(membroDTO, Membro.class);
        membroParaAtualizar.setId(id);
        membroParaAtualizar = membroService.atualizarParcialMembro(membroParaAtualizar);
        return  modelMapper.map(membroParaAtualizar, AtualizarMembroParcialDTO.class);
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarMembro(@PathVariable Integer id){
        membroService.deletarMembro(id);
    }
}

