package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private GuildaService guildaService;

    List<Guilda> listaDeGuildasDoMembro;

    public Membro cadastrarMembro(Membro membro) {
        listaDeGuildasDoMembro = guildaService.buscarGuildas(membro.getGuildas());
        verificarSeMembroERepresentanteEEstaEmMaisDeUmaGuilda(membro);
        membro.setGuildas(listaDeGuildasDoMembro);
        return membroRepository.save(membro);
    }

    public Iterable<Membro> retornarTodosOsMembros(){
       return membroRepository.findAll();
    }

    public Membro atualizarMembro(Membro membro){
        if(membroRepository.existsById(membro.getId())){
            Membro objMembro = membroRepository.save(membro);
            return membro;
        }

        throw new RecursoNaoEncontradoException("Membro", membro.getId());
    }

    public void verificarSeMembroERepresentanteEEstaEmMaisDeUmaGuilda(Membro membro){
        if(listaDeGuildasDoMembro.stream().count() > 0 && membro.isRepresentante() == true){
            throw new RuntimeException("O membro só pode ser representante de uma guilda");
        }
    }

    public void deletarMembro(Integer id) {
        if(membroRepository.existsById(id)){
            membroRepository.deleteById(id);
        }

        throw new RecursoNaoEncontradoException("Membro", id);
    }

}
