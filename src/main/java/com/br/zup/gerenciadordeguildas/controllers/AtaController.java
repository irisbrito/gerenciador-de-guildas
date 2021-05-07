package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.ata.AtaDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.ata.AtualizarAtaDTO;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtaDTO cadastrarAta(@RequestBody @Valid AtaDTO ataDTO) {
        Ata ata = modelMapper.map(ataDTO, Ata.class);
        ata = ataService.cadastrarAta(ata);

        return modelMapper.map(ata, AtaDTO.class);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ata> buscarAtas() {
        return ataService.buscarAtas();
    }

    @GetMapping("guilda/{nome}/")
    public Iterable<Ata> buscarAtasPeloNomeDaGuilda(@PathVariable String nome){
        return ataService.buscarAtasPeloNomeDaGuilda(nome);
    }

    @GetMapping("{id}/")
    public Ata buscarAtaPeloId(@PathVariable Integer id){
        return ataService.buscarAtaPeloId(id);
    }

    @PutMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public Ata atualizarAta(@PathVariable Integer id, @RequestBody AtualizarAtaDTO ataDTO){
        Ata ata = ataService.atualizarAta(ataDTO.converterDTOParaModel(id));

        return ata;
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarAta(@PathVariable Integer id){
        ataService.deletarAta(id);
    }
}
