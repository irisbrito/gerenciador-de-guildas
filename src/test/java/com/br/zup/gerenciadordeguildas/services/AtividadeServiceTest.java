package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.repositories.AtaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = AtividadeService.class)
public class AtividadeServiceTest {

    @Autowired
    private AtividadeService atividadeService;

    @MockBean
    private AtaRepository ataRepository;


    private Atividade atividade;
    private Guilda guilda;
    private Membro membro;
}
