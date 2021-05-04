package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.entities.Representante;
import com.br.zup.gerenciadordeguildas.services.RepresentanteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Representante cadastrarRepresentante(@RequestBody Representante representante){
        return representanteService.salvarRepresentante(representante);
    }

    @GetMapping
    public Iterable<Representante> listarRepresentantes(){
        return representanteService.retornarTodosOsRepresentantes();
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarRepresentante(@PathVariable Integer id){
        representanteService.deletarRepresentante(id);
    }
}

