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

    public Ata atualizarParcialAta(Ata ata){
        try {
            Ata objetoAta = buscarAtaPeloId(ata.getId());

            if (!objetoAta.getData().equals(ata.getData()) && ata.getData() != null) {
                objetoAta.setData(ata.getData());
            }

            if (!objetoAta.getPauta().equals(ata.getPauta()) && ata.getPauta() != null) {
                objetoAta.setPauta(ata.getPauta());
            }

            if (!objetoAta.getAssuntos().equals(ata.getAssuntos()) && ata.getAssuntos() != null) {
                objetoAta.setAssuntos(ata.getAssuntos());
            }

            if (!objetoAta.getGuilda().equals(ata.getGuilda()) && ata.getGuilda() != null) {
                objetoAta.setGuilda(ata.getGuilda());
            }

            return atualizarAta(objetoAta);
        }
        catch (Exception error){
            throw new RecursoNaoEncontradoException("Ata", ata.getId());
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
