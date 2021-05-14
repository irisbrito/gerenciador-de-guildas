package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.enums.Status;
import com.br.zup.gerenciadordeguildas.exceptions.ListaVaziaException;
import com.br.zup.gerenciadordeguildas.repositories.AtividadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = AtividadeService.class)
public class AtividadeServiceTest {

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private MembroService membroService;

    @MockBean
    private AtividadeRepository atividadeRepository;


    private Atividade atividade;
    private Membro membro;
    private Guilda guilda;

    @BeforeEach
    public void setUp() {

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
        this.atividade.setGuilda(guilda);
    }

    @Test
    @DisplayName("Deve cadastrar uma Atividade com sucesso")
    public void testarCadastrarAtividade() {
        Mockito.when(atividadeRepository.save(Mockito.any(Atividade.class))).thenReturn(atividade);

        Atividade atividadeTeste = atividadeService.cadastrarAtividade(atividade);
        assertEquals(atividadeTeste, atividade);
    }

    @Test
    @DisplayName("Deve buscar todas as Atividades com sucesso ou lançar a exceção ListaVaziaException caso não haja nenhuma Atividade cadastrada")
    public void testarBuscarAtividades() {
        Optional<Atividade> optionalAtividade = Optional.empty();

        Mockito.when(atividadeRepository.findById(Mockito.anyInt())).thenReturn(optionalAtividade);

        assertThrows(ListaVaziaException.class,() ->{
            atividadeService.buscarAtividades();
            throw new ListaVaziaException("atividades", 'a');
        });
    }

    @Test
    @DisplayName("Deve buscar uma Atividade pelo Id com sucesso")
    public void testarBuscarAtividadePeloId(){
        Optional<Atividade> optionalAtividade = Optional.of(this.atividade);
        Mockito.when(atividadeRepository.findById(Mockito.anyInt())).thenReturn(optionalAtividade);

        Atividade atividadeTest = atividadeService.buscarAtividadePeloId(1);

        assertSame(this.atividade, atividadeTest);
        assertEquals(atividade.getId(),1 );
    }
}
