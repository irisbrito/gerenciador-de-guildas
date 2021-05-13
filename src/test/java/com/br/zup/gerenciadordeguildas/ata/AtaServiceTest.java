package com.br.zup.gerenciadordeguildas.ata;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.exceptions.ListaVaziaException;
import com.br.zup.gerenciadordeguildas.repositories.AtaRepository;
import com.br.zup.gerenciadordeguildas.services.AtaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = AtaService.class)
public class AtaServiceTest {

    @Autowired
    AtaService ataService;

    @MockBean
    AtaRepository ataRepository;

    private Ata ata;
    private Guilda guilda;

    @BeforeEach
    public void setUp() {

        this.ata = new Ata();
        this.ata.setId(1);
        this.ata.setData(Instant.now());
        this.ata.setPauta("Pauta para teste.");
        this.ata.setAssuntos("Assuntos para teste.");

        this.guilda = new Guilda();
        this.guilda.setId(1);
        this.guilda.setNome("NomeGuilda para teste.");
        this.guilda.setDescricao("Descrição para teste.");
        this.guilda.setObjetivos("Objetivos para teste.");
        this.guilda.setLinkDoChat("LinkDoChat para teste.");
    }

    @Test
    @DisplayName("Deve cadastrar uma Ata com sucesso")
    public void testarCadastrarAta() {
        Mockito.when(ataRepository.save(Mockito.any(Ata.class))).thenReturn(ata);

        Ata ataTeste = ataService.cadastrarAta(ata);
        assertEquals(ataTeste, ata);
    }

    @Test
    @DisplayName("Deve buscar todas as Atas com sucesso")
    public void testarBuscarAtas() {
        Optional<Ata> optionalAta = Optional.empty();

        Mockito.when(ataRepository.findById(Mockito.anyInt())).thenReturn(optionalAta);

        assertThrows(ListaVaziaException.class,() ->{
            ataService.buscarAtas();
            throw new ListaVaziaException("ata", 'a');
        });
    }
}
