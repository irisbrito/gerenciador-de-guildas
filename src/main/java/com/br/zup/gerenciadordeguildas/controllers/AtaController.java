package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.services.AtaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ata> buscarAtas() {
        return ataService.buscarAtas();
    }


}
