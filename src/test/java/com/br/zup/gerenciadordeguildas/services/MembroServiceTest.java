package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.exceptions.ListaVaziaException;
import com.br.zup.gerenciadordeguildas.repositories.MembroRepository;
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
@ContextConfiguration(classes = MembroService.class)
public class MembroServiceTest {

    @Autowired
    private MembroService membroService;

    @MockBean
    private MembroRepository membroRepository;

    @MockBean
    private GuildaService guildaService;

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

        this.guilda = new Guilda();
        this.guilda.setId(1);
        this.guilda.setNome("NomeGuilda para teste.");
        this.guilda.setDescricao("Descrição para teste.");
        this.guilda.setObjetivos("Objetivos para teste.");
        this.guilda.setLinkDoChat("LinkDoChat para teste.");
        this.guilda.setMembros(Collections.singletonList(membro));

        this.membro.setGuilda(guilda);
    }

    @Test
    @DisplayName("Deve cadastrar um Membro com sucesso")
    public void testarCadastrarMembro() {
        Mockito.when(membroRepository.save(Mockito.any(Membro.class))).thenReturn(membro);

        Membro membroTeste = membroService.cadastrarMembro(membro);
        assertEquals(membroTeste, membro);
    }

    @Test
    @DisplayName("Deve buscar todos os Membros com sucesso ou lançar a exceção ListaVaziaException caso não haja nenhum Membro cadastrado")
    public void testarBuscarMembros() {
        Optional<Membro> optionalMembro = Optional.empty();

        Mockito.when(membroRepository.findById(Mockito.anyInt())).thenReturn(optionalMembro);

        assertThrows(ListaVaziaException.class,() ->{
            membroService.buscarMembros();
            throw new ListaVaziaException("membro", 'o');
        });
    }

    @Test
    @DisplayName("Deve buscar um Membro pelo Id com sucesso")
    public void testarBuscarMembroPeloId(){
        Optional<Membro> optionalMembro = Optional.of(this.membro);
        Mockito.when(membroRepository.findById(Mockito.anyInt())).thenReturn(optionalMembro);

        Membro membro = membroService.buscarMembroPeloId(1);

        assertSame(this.membro, membro);
        assertEquals(membro.getId(),1 );
    }
}
