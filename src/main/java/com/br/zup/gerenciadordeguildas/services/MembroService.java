package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    public Membro cadastrarMembro(Membro membro) {
        return membroRepository.save(membro);
    }

    public Iterable<Membro> retornarTodosOsMembros(){
       return membroRepository.findAll();
    }

    public Iterable<Membro> buscarMembrosPeloNomeDaGuilda(String nome){
        return membroRepository.findAllByGuildasNome(nome);
    }

    public Membro atualizarMembro(Membro membro){
        if(membroRepository.existsById(membro.getId())){
            Membro objMembro = membroRepository.save(membro);
            return membro;
        }

        throw new RecursoNaoEncontradoException("Membro", membro.getId());
    }

    public void deletarMembro(Integer id) {
        if(membroRepository.existsById(id)){
            membroRepository.deleteById(id);
        }

        throw new RecursoNaoEncontradoException("Membro", id);
    }

}
