package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.exceptions.ListaVaziaException;
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
        try{ listaDeGuildasDoMembro = guildaService.buscarGuildas(membro.getGuildas());
            // todo: rever regra - verificarSeMembroERepresentanteEEstaEmMaisDeUmaGuilda(membro);
            membro.setGuildas(listaDeGuildasDoMembro);
            return membroRepository.save(membro);}
        catch (Exception error){
            throw new RuntimeException("Não foi posssível cadastrar membro!");
        }
    }

    public Membro buscarMembroPeloId(int id){
        Optional<Membro> optionalMembro = membroRepository.findById(id);

        if(optionalMembro.isPresent()){
            return optionalMembro.get();
        }

        throw new RuntimeException("Membro não existe");
    }

    public Membro buscarMembroPeloEmail(String email){
        Optional<Membro> optionalMembro = membroRepository.findByEmail(email);

        if(optionalMembro.isPresent()){
            return optionalMembro.get();
        }

        throw new RuntimeException("Membro não existe");
    }


    public Iterable<Membro> retornarTodosOsMembros(){
        Iterable<Membro> listaDeMembros = membroRepository.findAll();

        if(listaDeMembros.iterator().hasNext()){
            return listaDeMembros;
        }

        throw new ListaVaziaException("membro", 'o');
    }

    public Membro atualizarMembro(Membro membro){
        if(membroRepository.existsById(membro.getId())){
            Membro objMembro = membroRepository.save(membro);
            return membro;
        }

        throw new RecursoNaoEncontradoException("Membro", membro.getId());
    }

    public Membro atualizarParcialMembro(Membro membroParaAtualizar){
        try {
            Membro membroAtualNoBD = buscarMembroPeloId(membroParaAtualizar.getId());

            if(!membroAtualNoBD.getNome().equals(membroParaAtualizar.getNome()) && membroParaAtualizar.getNome() != null ){
                membroAtualNoBD.setNome(membroParaAtualizar.getNome());
            }

            if (membroAtualNoBD.getEmail() != membroParaAtualizar.getEmail() && membroParaAtualizar.getEmail() != null){
                membroAtualNoBD.setEmail(membroParaAtualizar.getEmail());
            }

            if (membroAtualNoBD.getZenity() != membroParaAtualizar.getZenity() && membroParaAtualizar.getZenity() != null){
                membroAtualNoBD.setZenity(membroParaAtualizar.getZenity());
            }

            if (membroAtualNoBD.getRepresentante() != membroParaAtualizar.getRepresentante() && membroParaAtualizar.getRepresentante() != null){
                membroAtualNoBD.setRepresentante(membroParaAtualizar.getRepresentante());
            }

            return atualizarMembro(membroAtualNoBD);

        } catch (Exception error){
            throw new RecursoNaoEncontradoException("Membro", membroParaAtualizar.getId());}
    }
//
//    public void verificarSeMembroERepresentanteEEstaEmMaisDeUmaGuilda(Membro membro){
//        if(listaDeGuildasDoMembro.stream().count() > 0 && membro.isRepresentante()){
//            throw new RuntimeException("O membro só pode ser representante de uma guilda");
//        }
//    }

    public void deletarMembro(Integer id) {
        if(membroRepository.existsById(id)){
            membroRepository.deleteById(id);
        } else {
            throw new RecursoNaoEncontradoException("Membro", id);
        }
    }

    public Membro buscarMembroPeloNome(String nome) {
        Optional<Membro> membro = membroRepository.findByNome(nome);
        return membro.orElseThrow( () -> new RecursoNaoEncontradoException("Membro" +nome, null));
    }
}
