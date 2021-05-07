package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.membro.AtualizarMembroDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.membro.AtualizarParcialMembroDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.membro.CadastroMembroDTO;
import com.br.zup.gerenciadordeguildas.dtos.saida.membro.CadastroMembroDTOSaida;
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
        List<Guilda> guildas = new ArrayList<>();
        cadastroMembroDTO.getGuildas()
                         .forEach( nomeDaGuilda -> guildas.add(guildaService.buscarGuildaPeloNome(nomeDaGuilda)));
        Membro membro = membroService.cadastrarMembro(cadastroMembroDTO.converterDTOparaEntity(guildas));

        return CadastroMembroDTOSaida.converterEntityParaDTOSaida(membro);
    }

    @GetMapping
    public Iterable<Membro> listarMembros(){
        return membroService.retornarTodosOsMembros();
    }

    @PutMapping("{id}/")
    public Membro atualizarMembro(@PathVariable Integer id, @RequestBody AtualizarMembroDTO membroDTO){
        Membro membro = membroService.atualizarMembro(membroDTO.converterDTOParaModel(id));
        return membro;
    }

    @PatchMapping("{id}/")
    public Membro atualizarMembroParcial(@PathVariable int id,
                                         @RequestBody @Valid AtualizarParcialMembroDTO membroDTO){
        Membro membro = membroDTO.converterDTOParaModel(id);
        return membroService.atualizarParcialMembro(membro);
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarMembro(@PathVariable Integer id){
        membroService.deletarMembro(id);
    }
}

