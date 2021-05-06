package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.membro.AtualizarMembroDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.membro.AtualizarParcialMembroDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.membro.MembroDTO;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.services.MembroService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("membros/")
public class MembroController {

    private MembroService membroService;
    private ModelMapper modelMapper;

    public MembroController(MembroService membroService, ModelMapper modelMapper) {
        this.membroService = membroService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MembroDTO cadastrarMembro(@RequestBody @Valid MembroDTO membroDTO){
        Membro membro = modelMapper.map(membroDTO, Membro.class);
        membro = membroService.cadastrarMembro(membro);

        return modelMapper.map(membro, MembroDTO.class);
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

