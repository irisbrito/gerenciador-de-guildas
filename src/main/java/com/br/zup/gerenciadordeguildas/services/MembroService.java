package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private GuildaService guildaService;

    List<Guilda> listaDeGuildasDoMembro;

    public Membro cadastrarMembro(Membro membro) {
        listaDeGuildasDoMembro = guildaService.buscarGuildas(membro.getGuildas());
      // todo: rever regra - verificarSeMembroERepresentanteEEstaEmMaisDeUmaGuilda(membro);
        membro.setGuildas(listaDeGuildasDoMembro);
        return membroRepository.save(membro);
    }

    public Membro buscarMembroPeloId(int id){
        Optional<Membro> optionalMembro = membroRepository.findById(id);

        if(optionalMembro.isPresent()){
            return optionalMembro.get();
        }

        throw new RuntimeException("Membro não existe");
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

    public Membro atualizarParcialMembro(Membro membro){
        Membro  objetoMembro= buscarMembroPeloId(membro.getId());

        if(!objetoMembro.getNome().equals(membro.getNome()) && membro.getNome() != null ){
            objetoMembro.setNome(membro.getNome());
        }

        if (objetoMembro.getEmail() != membro.getEmail() && membro.getEmail() != null){
            objetoMembro.setEmail(membro.getEmail());
        }

        if (objetoMembro.getZenity() != membro.getZenity() && membro.getZenity() != null){
            objetoMembro.setZenity(membro.getZenity());
        }

        if (objetoMembro.getGuildas() != membro.getGuildas() && membro.getGuildas() != null){
            objetoMembro.setGuildas(membro.getGuildas());
        }

        return atualizarMembro(objetoMembro);
    }

    public void verificarSeMembroERepresentanteEEstaEmMaisDeUmaGuilda(Membro membro){
        if(listaDeGuildasDoMembro.stream().count() > 0 && membro.isRepresentante()){
            throw new RuntimeException("O membro só pode ser representante de uma guilda");
        }
    }

    public void deletarMembro(Integer id) {
        if(membroRepository.existsById(id)){
            membroRepository.deleteById(id);
        } else {
            throw new RecursoNaoEncontradoException("Membro", id);
        }
    }

}
