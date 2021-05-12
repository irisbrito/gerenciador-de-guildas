package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.exceptions.ListaVaziaException;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.AtaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AtaService {

    private AtaRepository ataRepository;

    public AtaService(AtaRepository ataRepository) {
        this.ataRepository = ataRepository;
    }

    public Iterable<Ata> buscarAtas() {
        Iterable<Ata> listaComTodasAtas = ataRepository.findAll();
        if(listaComTodasAtas.iterator().hasNext()) {
            return listaComTodasAtas;
        }
        throw new ListaVaziaException("Ata", 'a');
    }

    public Iterable<Ata> buscarAtasPeloNomeDaGuilda(String guilda){
        Iterable<Ata> listaAtas = ataRepository.findAllByGuilda(guilda);
        if(listaAtas.iterator().hasNext()){
            return listaAtas;
        }
        throw new ListaVaziaException("atas", 'a');
    }

    public Ata buscarAtaPeloId(int id){
        Optional<Ata> optionalAta = ataRepository.findById(id);

        if(optionalAta.isPresent()){
            return optionalAta.get();
        }

        throw new RecursoNaoEncontradoException("Ata", id);
    }

    public Ata cadastrarAta(Ata ata) {
        ata.setData(LocalDate.now());
        return ataRepository.save(ata);
    }

    public Ata atualizarAta(Ata ata){
        if(ataRepository.existsById(ata.getId())){
            Ata objAta = cadastrarAta(ata);
            return ata;
        }

        throw new RecursoNaoEncontradoException("Ata", ata.getId());
    }

    public void atualizarDadosDaAta(Ata ata, Ata dadosParaAtualizar) {

        if (!ata.getPauta().equals(dadosParaAtualizar.getPauta()) && dadosParaAtualizar.getPauta() != null) {
            ata.setPauta(dadosParaAtualizar.getPauta());
        }

        if (!ata.getAssuntos().equals(dadosParaAtualizar.getAssuntos()) && dadosParaAtualizar.getAssuntos() != null) {
            ata.setAssuntos(dadosParaAtualizar.getAssuntos());
        }

        if (ata.getGuilda() != dadosParaAtualizar.getGuilda() && dadosParaAtualizar.getGuilda() != null) {
            ata.setGuilda(dadosParaAtualizar.getGuilda());
        }
    }

    public Ata atualizarAtaParcial(Ata dadosParaAtualizar){
        try {
            Ata ata = buscarAtaPeloId(dadosParaAtualizar.getId());
            atualizarDadosDaAta(ata,dadosParaAtualizar);

            return atualizarAta(ata);
        }
        catch (Exception error){
            throw new RecursoNaoEncontradoException("Ata", dadosParaAtualizar.getId());
        }
    }

    public void deletarAta(Integer id) {
        if(ataRepository.existsById(id)){
            ataRepository.deleteById(id);
        } else {
            throw new RecursoNaoEncontradoException("Ata", id);
        }
    }
}
