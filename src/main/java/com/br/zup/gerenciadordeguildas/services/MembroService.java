package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.exceptions.EmailExistenteException;
import com.br.zup.gerenciadordeguildas.exceptions.ListaVaziaException;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.AtividadeRepository;
import com.br.zup.gerenciadordeguildas.repositories.MembroRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class MembroService {

    private MembroRepository membroRepository;
    private AtividadeService atividadeService;
    private GuildaService guildaService;
    private AtividadeRepository atividadeRepository;

    public MembroService(@Lazy MembroRepository membroRepository, @Lazy AtividadeService atividadeService, @Lazy GuildaService guildaService, @Lazy AtividadeRepository atividadeRepository) {
        this.membroRepository = membroRepository;
        this.atividadeService = atividadeService;
        this.guildaService = guildaService;
        this.atividadeRepository = atividadeRepository;
    }

    public Membro cadastrarMembro(Membro membro) {
        verificarEmailCadastrado(membro.getEmail());
        Membro objMembro = membroRepository.save(membro);
        guildaService.adicionarMembroNaGuilda(membro.getGuilda().getId(), membro.getId());

        return objMembro;
    }

    public Membro buscarMembroPeloId(int id){
        Optional<Membro> optionalMembro = membroRepository.findById(id);

        if(optionalMembro.isPresent()){
            return optionalMembro.get();
        }

        throw new RecursoNaoEncontradoException("Membro", id);
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

            if (membroAtualNoBD.isRepresentante() != membroParaAtualizar.isRepresentante()){
                membroAtualNoBD.setRepresentante(membroParaAtualizar.isRepresentante());
            }

            return atualizarMembro(membroAtualNoBD);

        } catch (Exception error){
            throw new RecursoNaoEncontradoException("Membro", membroParaAtualizar.getId());}
    }

    public boolean verificarEmailCadastrado(String email){
        if(!membroRepository.existsByEmail(email)){
            return true;
        } else {
            throw new EmailExistenteException();
        }
    }

    public List<Atividade> buscarAtividadesDeUmResponsavel(Integer idDoMembro){
        Optional<Membro> membroOptional = membroRepository.findById(idDoMembro);

        membroOptional.orElseThrow(() -> new RecursoNaoEncontradoException("Responsável", idDoMembro));

      return atividadeRepository.findByResponsaveis_id(idDoMembro);
    }

    public void deletarMembro(Integer id) {

        if(membroRepository.existsById(id)){
            if(!atividadeRepository.findByResponsaveis_id(id).isEmpty()){
                throw new RuntimeException("Não é possível deletar o membro, pois ele tem atividades associadas");
            }
            membroRepository.deleteById(id);
        } else {
            throw new RecursoNaoEncontradoException("Membro", id);
        }
    }

    public Membro buscarMembroPeloNome(String nome) {
        Optional<Membro> membro = membroRepository.findByNome(nome);
        return membro.orElseThrow( () -> new RecursoNaoEncontradoException("Membro" , nome));
    }
}
