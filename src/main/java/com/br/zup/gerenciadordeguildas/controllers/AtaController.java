package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.dtos.entrada.ata.AtualizarParcialAtaDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.ata.CadastroAtaDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.ata.AtualizarAtaDTO;
import com.br.zup.gerenciadordeguildas.dtos.saida.ata.CadastroAtaDTOSaida;
import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.services.AtaService;
import com.br.zup.gerenciadordeguildas.services.GuildaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("atas/")
public class AtaController {

    private AtaService ataService;
    private GuildaService guildaService;
    private ModelMapper modelMapper;

    public AtaController(AtaService ataService, GuildaService guildaService, ModelMapper modelMapper) {
        this.ataService = ataService;
        this.guildaService = guildaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadastroAtaDTOSaida cadastrarAta(@RequestBody @Valid CadastroAtaDTO cadastroAtaDTO) {
        Guilda guilda = guildaService.buscarGuildaPeloNome(cadastroAtaDTO.getGuilda());
        Ata ata = ataService.cadastrarAta(cadastroAtaDTO.converterDTOParaEntity(guilda));

        return CadastroAtaDTOSaida.converterEntityParaDTOSaida(ata);
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

    @PatchMapping("{id}/")
    public Ata atualizarAtaParcial(@PathVariable int id,
                                   @RequestBody @Valid AtualizarParcialAtaDTO ataDTO){
        Ata ata = ataDTO.converterDTOParaModel(id);
        return ataService.atualizarParcialAta(ata);
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deletarAta(@PathVariable Integer id){
        ataService.deletarAta(id);
    }
}
