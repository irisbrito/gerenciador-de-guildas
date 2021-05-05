package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.representante.AtualizarRepresentanteDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.representante.RepresentanteDTO;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.services.RepresentanteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("representantes/")
public class RepresentanteController {

    private RepresentanteService representanteService;
    private ModelMapper modelMapper;

    public RepresentanteController(RepresentanteService representanteService, ModelMapper modelMapper) {
        this.representanteService = representanteService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RepresentanteDTO cadastrarRepresentante(@RequestBody @Valid RepresentanteDTO representanteDTO){
        Membro membro = modelMapper.map(representanteDTO, Membro.class);
        membro = representanteService.cadastrarRepresentante(membro);

        return modelMapper.map(membro, RepresentanteDTO.class);
    }

    @GetMapping
    public Iterable<Membro> listarRepresentantes(){
        return representanteService.retornarTodosOsRepresentantes();
    }

    @GetMapping("guilda/{nome}/")
    public Iterable<Membro> buscarRepresentantesPeloNomeDaGuilda(@PathVariable String nome){
        return representanteService.buscarRepresentantesPeloNomeDaGuilda(nome);
    }

    @PutMapping("{id}/")
    public Membro atualizarRepresentante(@PathVariable Integer id, @RequestBody AtualizarRepresentanteDTO representanteDTO){
        Membro membro = representanteService.atualizarRepresentante(representanteDTO.converterDTOParaModel(id));
        return membro;
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarRepresentante(@PathVariable Integer id){
        representanteService.deletarRepresentante(id);
    }
}

