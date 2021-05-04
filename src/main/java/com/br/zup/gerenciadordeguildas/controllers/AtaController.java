package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.services.AtaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("atas/")
public class AtaController {

    private AtaService ataService;

    public AtaController(AtaService ataService) {
        this.ataService = ataService;
    }


    
}
