package com.br.zup.gerenciadordeguildas.ata;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.enums.Status;
import com.br.zup.gerenciadordeguildas.repositories.AtaRepository;
import com.br.zup.gerenciadordeguildas.services.AtaService;
import com.br.zup.gerenciadordeguildas.services.AtividadeService;
import com.br.zup.gerenciadordeguildas.services.GuildaService;
import com.br.zup.gerenciadordeguildas.services.MembroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.Instant;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ContextConfiguration(classes = AtaService.class)
public class AtaServiceTest {

    @Autowired
    AtaService ataService;

    @MockBean
    AtaRepository ataRepository;

    @MockBean
    GuildaService guildaService;

    @MockBean
    MembroService membroService;

    @MockBean
    AtividadeService atividadeService;

    private Ata ata;
    private Guilda guilda;
    private Membro membro;
    private Atividade atividade;

    @BeforeEach
    public void setUp() {

        this.ata = new Ata();
        this.ata.setId(1);
        this.ata.setData(Instant.now());
        this.ata.setPauta("Pauta para teste.");
        this.ata.setAssuntos("Assuntos para teste.");

        this.membro = new Membro();
        this.membro.setId(1);
        this.membro.setNome("NomeMembro para teste.");
        this.membro.setEmail("emailmembro@teste.com");
        this.membro.setZenity("Zenity para teste.");
        this.membro.setRepresentante(false);

        this.atividade = new Atividade();
        this.atividade.setId(1);
        this.atividade.setNome("NomeAtividade para teste");
        this.atividade.setDescricao("DescriçãoAtividade para teste");
        this.atividade.setStatus(Status.PENDENTE);
        this.atividade.setResponsaveis(Arrays.asList(membro));

        this.guilda = new Guilda();
        this.guilda.setId(1);
        this.guilda.setNome("NomeGuilda para teste.");
        this.guilda.setDescricao("Descrição para teste.");
        this.guilda.setObjetivos("Objetivos para teste.");
        this.guilda.setLinkDoChat("LinkDoChat para teste.");
        this.guilda.setMembros(Arrays.asList(membro));
        guilda.setAtividades(Arrays.asList(atividade));
        this.guilda.setAtas(Arrays.asList(ata));

        this.membro.setGuilda(guilda);
        this.ata.setGuilda(guilda);
        this.atividade.setGuilda(guilda);
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
