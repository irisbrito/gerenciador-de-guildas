package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.enums.Status;
import com.br.zup.gerenciadordeguildas.repositories.GuildaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.Instant;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ContextConfiguration(classes = GuildaService.class)
public class GuildaServiceTest {

    @Autowired
    private GuildaService guildaService;

    @MockBean
    private GuildaRepository guildaRepository;

    private Ata ata;
    private Guilda guilda;
    private Membro membro;
    private Atividade atividade;

    @BeforeEach
    public void setUp() {

        this.membro = new Membro();
        this.membro.setId(1);
        this.membro.setNome("NomeMembro para teste.");
        this.membro.setEmail("emailmembro@teste.com");
        this.membro.setZenity("Zenity para teste.");
        this.membro.setRepresentante(false);

        this.ata = new Ata();
        this.ata.setId(1);
        this.ata.setData(Instant.now());
        this.ata.setPauta("Pauta para teste.");
        this.ata.setAssuntos("Assuntos para teste.");

        this.atividade = new Atividade();
        this.atividade.setId(1);
        this.atividade.setNome("NomeAtividade para teste");
        this.atividade.setDescricao("DescriçãoAtividade para teste");
        this.atividade.setStatus(Status.PENDENTE);
        this.atividade.setResponsaveis(Collections.singletonList(membro));

        this.guilda = new Guilda();
        this.guilda.setId(1);
        this.guilda.setNome("NomeGuilda para teste.");
        this.guilda.setDescricao("Descrição para teste.");
        this.guilda.setObjetivos("Objetivos para teste.");
        this.guilda.setLinkDoChat("LinkDoChat para teste.");
        this.guilda.setMembros(Collections.singletonList(membro));
        this.guilda.setAtividades(Collections.singletonList(atividade));

        this.membro.setGuilda(guilda);
        this.ata.setGuilda(guilda);
        this.atividade.setGuilda(guilda);
    }

    @Test
    @DisplayName("Deve cadastrar uma Guilda com sucesso")
    public void testarCadastrarGuilda() {
        Mockito.when(guildaRepository.save(Mockito.any(Guilda.class))).thenReturn(guilda);

        Guilda guildaTeste = guildaService.cadastrarGuilda(guilda);
        assertEquals(guildaTeste, guilda);
    }
}
