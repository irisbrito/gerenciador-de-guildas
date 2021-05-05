package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.AtaDTO;
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

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ata> buscarAtas() {
        return ataService.buscarAtas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtaDTO cadastrarAta(@RequestBody @Valid AtaDTO ataDTO) {
        Ata ata = modelMapper.map(ataDTO, Ata.class);
        ata = ataService.cadastrarAta(ata);

        return modelMapper.map(ata, AtaDTO.class);
    }
}
