package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.exceptions.ListaVaziaException;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private MembroService membroService;

    public Atividade cadastrarAtividade(Atividade atividade){
        return atividadeRepository.save(atividade);
    }

    public Iterable<Atividade> buscarAtividades(){
        Iterable<Atividade> listaDeAtividades = atividadeRepository.findAll();

        if(listaDeAtividades.iterator().hasNext()){
            return listaDeAtividades;
        }

        throw new ListaVaziaException("atividade", 'a');
    }

    public Atividade buscarAtividadePelaGuilda(String guilda) {
        try{
            return atividadeRepository.findByGuilda(guilda).get();
        }catch (Exception error){
            throw new RecursoNaoEncontradoException("Guilda", null);
        }
    }

    public Atividade buscarAtividadePeloId(int id){
        Optional<Atividade> optionalAtividade = atividadeRepository.findById(id);

        if(optionalAtividade.isPresent()){
            return optionalAtividade.get();
        }

        throw new RuntimeException("Atividade não existe");
    }

    public Atividade atualizarAtividade(Atividade atividade){
        if(atividadeRepository.existsById(atividade.getId())){
            Atividade objAtividade = cadastrarAtividade(atividade);
            return atividade;
        }

        throw new RecursoNaoEncontradoException("Atividade", atividade.getId());
    }

    public Atividade atualizarParcialAtividade(Atividade atividade){
        try {
            Atividade objetoAtividade = buscarAtividadePeloId(atividade.getId());

            if (!objetoAtividade.getNome().equals(atividade.getNome()) && atividade.getNome() != null) {
                objetoAtividade.setNome(atividade.getNome());
            }

            if (objetoAtividade.getDescricao() != atividade.getDescricao() && atividade.getDescricao() != null) {
                objetoAtividade.setDescricao(atividade.getDescricao());
            }

            if (objetoAtividade.getStatus() != atividade.getStatus() && atividade.getStatus() != null) {
                objetoAtividade.setStatus(atividade.getStatus());
            }

            return atualizarAtividade(objetoAtividade);
        }
        catch (Exception error){
            throw new RecursoNaoEncontradoException("Atividade", atividade.getId());
        }
    }

    public void deletarResponsavelAtividade(Integer idDaAtividade, Integer idDoMembro) {
        try {
            Atividade atividade = buscarAtividadePeloId(idDaAtividade);
            Membro membro = membroService.buscarMembroPeloId(idDoMembro);
            atividade.getResponsaveis().remove(membro);
        } catch (Exception error) {
            throw new RuntimeException("Não foi possível deletar o responsável");
        }
    }

    public Atividade adicionarRepresentanteNaAtividade(Integer idDaAtividade, Integer idDoMembro){
        Atividade atividade = buscarAtividadePeloId(idDaAtividade);
        Membro membro = membroService.buscarMembroPeloId(idDoMembro);

        if (!atividade.getResponsaveis().contains(idDoMembro)) {
            atividade.getResponsaveis().add(membro);
        }
        atividade.getResponsaveis().add(membro);
        return atividadeRepository.save(atividade);
    }

    public void deletarAtividade(Integer id){
        if(atividadeRepository.existsById(id)){
            atividadeRepository.deleteById(id);
        } else {
            throw new RecursoNaoEncontradoException("Atividade", id);
        }
    }
}
