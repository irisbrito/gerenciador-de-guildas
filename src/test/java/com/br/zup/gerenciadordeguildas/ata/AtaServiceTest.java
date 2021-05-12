package com.br.zup.gerenciadordeguildas.ata;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.repositories.AtaRepository;
import com.br.zup.gerenciadordeguildas.services.AtaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AtaServiceTest {

    @Autowired
    AtaService ataService;

    @MockBean
    AtaRepository ataRepository;

    private Ata ata;
    private Guilda guilda;
    private Membro membro;

    @BeforeEach
    public void setUp() {
        this.membro = new Membro();
        membro.setId(1);
        membro.setNome("NomeMembro para teste.");
        membro.setEmail("emailmembro@teste.com");
        membro.setZenity("Zenity para teste.");
        membro.setRepresentante(false);
        //membro.setGuilda(); verificar com o mentor

        this.guilda = new Guilda();
        guilda.setId(1);
        guilda.setNome("NomeGuilda para teste.");
        guilda.setDescricao("Descrição para teste.");
        guilda.setObjetivos("Objetivos para teste.");
        guilda.setLinkDoChat("LinkDoChat para teste.");
        //guilda.setMembros(); verificar com o mentor
        //guilda.setAtividades(); verificar com o mentor
        //guilda.setAtas(); verificar com o mentor

        this.ata = new Ata();
        ata.setId(1);
        ata.setData(Instant.now());
        ata.setPauta("Pauta para teste.");
        ata.setAssuntos("Assuntos para teste.");
        ata.setGuilda(guilda);
    }

    @Test
    public void testarCadastrarAta() {
//        Mockito.when(ataService.buscarAtaPeloId(Mockito.anyInt())).thenReturn(this.ata);
//        Mockito.when(ataRepository.save(Mockito.any(Ata.class))).thenReturn(this.ata);
//
//        Ata ata = ataService.cadastrarAta(this.ata);
//
//        Mockito.verify(ataRepository, Mockito.times(1)).save(this.ata);

        Mockito.when(ataRepository.save(ata)).thenReturn(ata);

        Ata ataCadastrada = ataRepository.save(ata);

        assertThat(ataCadastrada.getId()).isNotNull();
        assertThat(ataCadastrada.getData()).isBeforeOrEqualTo(Instant.now());
        assertThat(ataCadastrada.getPauta()).isEqualTo("Pauta para teste.");
        assertThat(ataCadastrada.getAssuntos()).isEqualTo("Assuntos para teste.");
        assertThat(ataCadastrada.getGuilda()).isNotNull();
    }
}
