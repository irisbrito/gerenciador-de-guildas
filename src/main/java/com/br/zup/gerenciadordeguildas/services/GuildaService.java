package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.GuildaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuildaService {

    @Autowired
    private GuildaRepository guildaRepository;

    public Guilda cadastrarGuilda(Guilda guilda) {
        return guildaRepository.save(guilda);
    }

    public Iterable<Guilda> retornarTodasAsGuildas(){
        return guildaRepository.findAll();
    }

    public Guilda buscarGuildaPeloNome(String nome){
        Optional<Guilda> optionalGuilda = guildaRepository.findByNome(nome);

        if(optionalGuilda.isPresent()){
            return optionalGuilda.get();
        }

        throw new RecursoNaoEncontradoException("Guilda", null);
    }

    public Guilda atualizarGuilda(Guilda guilda){
        if(guildaRepository.existsById(guilda.getId())){
            Guilda objGuilda = cadastrarGuilda(guilda);
            return guilda;
        }

        throw new RecursoNaoEncontradoException("Guilda", guilda.getId());
    }

    public void deletarGuilda(Integer id) {
        if(guildaRepository.existsById(id)){
            guildaRepository.deleteById(id);
        }

        throw new RecursoNaoEncontradoException("Guilda", id);
    }
}
