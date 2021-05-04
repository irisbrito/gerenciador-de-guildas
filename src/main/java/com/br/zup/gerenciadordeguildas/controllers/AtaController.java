package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.services.AtaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("atas/")
public class AtaController {

    private AtaService ataService;
    private ModelMapper modelMapper;

    public AtaController(AtaService ataService, ModelMapper modelMapper) {
        this.ataService = ataService;
        this.modelMapper = modelMapper;
    }

    public AtaController(AtaService ataService) {
        this.ataService = ataService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtaDTO adicionarAta(@RequestBody @Valid AtaDTO ataDTO){
        Ata ata = modelMapper.map(ataDTO, Ata.class);
        ata = ataService.cadastrarAtas(ata);

        return modelMapper.map(ata, AtaDTO.class);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ata> buscarAtas() {
        return ataService.buscarAtas();
    }


}
