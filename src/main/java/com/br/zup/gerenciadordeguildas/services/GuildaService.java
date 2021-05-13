package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoDuplicadoException;
import com.br.zup.gerenciadordeguildas.exceptions.ListaVaziaException;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.GuildaRepository;
import com.br.zup.gerenciadordeguildas.repositories.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
public class GuildaService {

    private GuildaRepository guildaRepository;
    private MembroService membroService;
    private AtaService ataService;
    private AtividadeService atividadeService;
    private MembroRepository membroRepository;

    public GuildaService(GuildaRepository guildaRepository, MembroService membroService, AtaService ataService, AtividadeService atividadeService, MembroRepository membroRepository) {
        this.guildaRepository = guildaRepository;
        this.membroService = membroService;
        this.ataService = ataService;
        this.atividadeService = atividadeService;
        this.membroRepository = membroRepository;
    }

    public Guilda cadastrarGuilda(Guilda guilda) {
        validarNomeGuilda(guilda.getNome());
        return guildaRepository.save(guilda);
    }

    public Guilda adicionarMembroNaGuilda(Integer idDaGuilda, Integer idDoMembro){
        Guilda guilda = buscarGuildaPeloId(idDaGuilda);
        Membro membro = membroService.buscarMembroPeloId(idDoMembro);
        guilda.getMembros().add(membro);

        return guildaRepository.save(guilda);
    }

    public Guilda adicionarAtaNaGuilda(Integer idDaGuilda, Integer idDaAta){
        Guilda guilda = buscarGuildaPeloId(idDaGuilda);
        Ata ata = ataService.buscarAtaPeloId(idDaAta);
        guilda.getAtas().add(ata);

        return guildaRepository.save(guilda);
    }

    public Guilda adicionarAtividadeNaGuilda(Integer idDaGuilda, Integer idDaAtividade){
        Guilda guilda = buscarGuildaPeloId(idDaGuilda);
        Atividade atividade = atividadeService.buscarAtividadePeloId(idDaAtividade);
        guilda.getAtividades().add(atividade);

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

    public Iterable<Membro> buscarRepresentantesDaGuilda(Guilda guilda){
        Iterable<Membro> listaDeRepresentantesDaGuilda = membroRepository.findAllByRepresentanteIsAndGuilda(true, guilda);

        if(listaDeRepresentantesDaGuilda.iterator().hasNext()){
            return listaDeRepresentantesDaGuilda;
        } else {
            throw new ListaVaziaException("representante", 'o');
        }
    }

    public void validarNomeGuilda(String nome){
        if(guildaRepository.existsByNome(nome)){
            throw new RecursoDuplicadoException("Guilda com nome já cadastrado!");
        }
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

    public void deletarMembroDaGuilda(Integer idDaGuilda, Integer idDoMembro){
        try{
            Guilda guilda = buscarGuildaPeloId(idDaGuilda);
            Membro membro = membroService.buscarMembroPeloId(idDoMembro);
            guilda.getMembros().remove(membro);
        }catch (Exception error){
            throw new RuntimeException("Não foi possível deletar o membro");
        }
    }

    public void deletarAtividadeDaGuilda(Integer idDaGuilda, Integer idDaAtividade){
        try{
            Guilda guilda = buscarGuildaPeloId(idDaGuilda);
            Atividade atividade = atividadeService.buscarAtividadePeloId(idDaAtividade);
            guilda.getAtividades().remove(atividade);
        }catch (Exception error){
            throw new RuntimeException("Não foi possível deletar a atividade!");
        }
    }

    public void deletarAtaDaGuilda(Integer idDaGuilda, Integer idDaAta){
        try{
            Guilda guilda = buscarGuildaPeloId(idDaGuilda);
            Ata ata = ataService.buscarAtaPeloId(idDaAta);
            guilda.getAtas().remove(ata);
        }catch (Exception error){
            throw new RuntimeException("Não foi possível deletar a ata!");
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
