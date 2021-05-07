package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.exceptions.ListaVaziaException;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.GuildaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
public class GuildaService {

    @Autowired
    private GuildaRepository guildaRepository;

    public Guilda cadastrarGuilda(Guilda guilda) {
        return guildaRepository.save(guilda);
    }

    public Iterable<Guilda> retornarTodasAsGuildas(){
        Iterable<Guilda> listaDeGuildas = guildaRepository.findAll();

        if(listaDeGuildas.iterator().hasNext()){
            return listaDeGuildas;
        }

        throw new ListaVaziaException("guilda", 'a');
    }

    public Guilda buscarGuildaPeloNome(String nome){
        Optional<Guilda> optionalGuilda = guildaRepository.findByNome(nome);

        if(optionalGuilda.isPresent()){
            return optionalGuilda.get();
        }

        throw new RecursoNaoEncontradoException("Guilda", null);
    }

    public Guilda buscarGuildaPeloId(Integer id){
        Optional<Guilda> optionalGuilda = guildaRepository.findById(id);

        if(optionalGuilda.isPresent()){
            return optionalGuilda.get();
        }

        throw new RecursoNaoEncontradoException("Guilda", id);
    }

    public List<Guilda> buscarGuildas(List<Guilda> guildas){
        List<Guilda> listaDeGuildas = new ArrayList<>();

        for (Guilda guilda : guildas) {
           Optional<Guilda> objGuilda = guildaRepository.findById(guilda.getId());
           if(Objects.nonNull(objGuilda)){
               listaDeGuildas.add(guilda);
            }else{
                throw new RecursoNaoEncontradoException("Guilda", guilda.getId());

            }
        }

        return listaDeGuildas;
    }

    public Guilda atualizarGuilda(Guilda guilda){
        if(guildaRepository.existsById(guilda.getId())){
            Guilda objGuilda = cadastrarGuilda(guilda);
            return guilda;
        }

        throw new RecursoNaoEncontradoException("Guilda", guilda.getId());
    }

    public Guilda atualizarGuildaParcial(Guilda guilda){
        try{
            Guilda objetoGuilda = buscarGuildaPeloId(guilda.getId());

            if(!objetoGuilda.getNome().equals(guilda.getNome()) && objetoGuilda.getNome() != null ){
                objetoGuilda.setNome(guilda.getNome());
            }

            if(!objetoGuilda.getDescricao().equals(guilda.getDescricao()) && objetoGuilda.getDescricao() != null){
                objetoGuilda.setDescricao(guilda.getDescricao());
            }

            if(!objetoGuilda.getObjetivos().equals(guilda.getObjetivos()) && objetoGuilda.getObjetivos() != null){
                objetoGuilda.setObjetivos(guilda.getObjetivos());
            }

            if(!objetoGuilda.getLinkDoChat().equals(guilda.getLinkDoChat()) && objetoGuilda.getLinkDoChat() != null){
                objetoGuilda.setLinkDoChat(guilda.getLinkDoChat());
            }

            return objetoGuilda;
        }
        catch (Exception error){
            throw new RecursoNaoEncontradoException("Guilda", guilda.getId());
        }
    }

    public void deletarGuilda(Integer id) {
        if(guildaRepository.existsById(id)){
            guildaRepository.deleteById(id);
        } else {
            throw new RecursoNaoEncontradoException("Guilda", id);
        }
    }
}
