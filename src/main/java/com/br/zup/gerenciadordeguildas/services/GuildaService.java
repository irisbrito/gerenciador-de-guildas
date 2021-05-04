package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Representante;
import com.br.zup.gerenciadordeguildas.repositories.GuildaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuildaService {

    @Autowired
    private GuildaRepository guildaRepository;

    public Guilda cadastrarGuilda(Guilda guilda) {
        return guildaRepository.save(guilda);
    }

}
