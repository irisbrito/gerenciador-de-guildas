package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.atividade.AtividadeDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.representante.AtualizarRepresentanteDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.representante.RepresentanteDTO;
import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Representante;
import com.br.zup.gerenciadordeguildas.services.RepresentanteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
        Representante representante = modelMapper.map(representanteDTO, Representante.class);
        representante = representanteService.cadastrarRepresentante(representante);

        return modelMapper.map(representante, RepresentanteDTO.class);
    }

    @GetMapping
    public Iterable<Representante> listarRepresentantes(){
        return representanteService.retornarTodosOsRepresentantes();
    }

    @PutMapping("{id}/")
    public Representante atualizarRepresentante(@PathVariable Integer id, @RequestBody AtualizarRepresentanteDTO representanteDTO){
        Representante representante = representanteService.atualizarRepresentante(representanteDTO.converterDTOParaModel(id));
        return representante;
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarRepresentante(@PathVariable Integer id){
        representanteService.deletarRepresentante(id);
    }
}

