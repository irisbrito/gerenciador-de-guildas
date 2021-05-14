# Gerenciador de Guildas da Zup! Documentação - Versão 1.0


## Apresentação

O Gerenciador de Guildas é um projeto desenvolvido e entregue na conclusão da 1° Turma do Catalisa da ZUP, que é um programa que visa capacitar PCDs para o mercado de trabalho na área de desenvolvimento Back-end.

O Gerenciador de Guildas da Zup é uma ferramenta que visa colaborar com a organização das guildas em relação aos encontros, representantes e facilitando a administração, para que todos tenham acesso ao andamento de suas atividades e propostas. E assim, as Guildas ganham mais visibilidade para que mais pessoas se interessem em fazer parte.

## Funcionalidades

1.  Centralizar as informações das guildas em um só lugar, como objetivos, descrição da guilda e representantes, grupo de chat.
2.  Fazer com que seja possível visualizar as guildas existentes
3.  Cadastrar os representantes da guilda
4.  Cadastrar novas Guildas
5.  Permitir o cadastro de novos membros na Guilda
6.  Adicionar atividades da Guilda
7.  Visualizar atividades das Guildas
10.  Cadastrar atas da Guilda

## Linguagem e Banco de Dados Utilizados:
-   Java
-   MariaDB.

## Ferramentas necessárias:
-   Intellij
-   Postman

## Dependências utilizadas:
-   Spring Boot
-   MySQL Driver
-   Spring Data JPA
-   Bean Validation (Hibernate)
-   Lombok
-   Spring Security
-   Model Mapper
-   Json Web Token
-   JUnit
-   Mockito

## Rotas
### GET

Retornar todas as guildas: localhost:8080/guildas/
Retornar a guilda pelo nome: localhost:8080/guildas/nome/
* Buscar todos os membros (endpoint não disponível à todos): localhost:8080/membros/
* Buscar membro pelo id: localhost:8080/membros/id/
  Retornar todos as atividades: localhost:8080/atividades/
  Retornar atividade pelo id: localhost:8080/atividades/id/
  Retornar todas as atas: localhost:8080/atas/
  Retornar ata pelo id: localhost:8080/atas/id/

### POST
**Cadastrar um novo usuário**: localhost:8080/usuarios/
O body deve conter:
{
"nomeCompleto": "Katniss Everdeen",
"email": "katniss@gmail.com",
"senha": 111111
}

**Cadastrar nova guilda**: localhost:8080/guildas/
O body deve ser:
{
"nome": "Zup Ladies",
"descricao": "Guilda para mulheres da Zup!",
"objetivos": "A Zup Ladies tem como intuito aproximar as mulheres e incentivar o fomento de mulheres na área de tecnologia",
"linkDoChat": "https://mail.google.com/mail/u/0/?tab=rm&ogbl#chat/space/AAAAtJ7hfhfuhhhzzjY"
}

**Cadastrar novo membro**: localhost:8080/membros/
Body:
{
"nome": "Juliana Matos",
"email": "juliana.matos@zup.com.br",
"zenity": "kshkjdgjkdgj",
"representante": "true",
"guilda": "Zup Ladies"
}

**Cadastrar nova atividade**: localhost:8080/atividades/
Body:
{
"nome": "Encontro semanal - maio 2021",
"descricao": "Primeiro encontro do mês das Zup Ladies",
"responsaveis": ["Juliana Matos"],
"status": "CONCLUIDA",
"guilda": "Zup Ladies"
}

**Cadastrar nova ata:** localhost:8080/atas/
{
"data": "2022-06-06",
"pauta": "Filmes com mulheres fortes",
"assuntos": "feminismo, poder feminino, personagens fortes",
"guilda": "Zup Ladies"
}

### PUT
Atualizar Atividade: localhost:8080/atividades/id/
Atualizar Ata: localhost:8080/atas/id/


**Atualizar Atividade**: localhost:8080/atividades/id/
O body deve ser:
{
"nome": "Encontro semanal - maio 2021",
"descricao": "Primeiro encontro do mês da ZuPride",
"responsaveis": ["Ana Alves"],
"status": "CONCLUIDA",
"guilda": "ZuPride"
}


**Atualizar Ata**: localhost:8080/atas/id/
Body:
{
"data": "2022-07-07",
"pauta": "Filmes LGBTs",
"assuntos": "personagens fortes",
"guilda": "ZuPride"
}


### PATCH
Atualizar Guilda Parcial: localhost:8080/guildas/id/
Atualizar Atividade Parcial: localhost:8080/atividades/id/
Atualizar Ata Parcial: localhost:8080/atas/id/
Atualizar Membro Parcial: localhost:8080/membros/id/

**Atualizar Guilda Parcial**: localhost:8080/guildas/id/
O body deve ser:
{
"nome": "ZuPride",
"descricao": "Guilda para LGBTQIA+",
"objetivos": "A ZuPride preza pelo acolhimento e fomento da comunidade LGBTQIA+ na área da tecnologia",
"linkDoChat":”https://mail.google.com/mail/u/0/?tab=rm&ogbl#chat/space/AAAAE2jzjYw"
}

**Atualizar Atividade Parcial**: localhost:8080/atividades/id/

{
"nome": "ZuPride",
"descricao": "Guilda para LGBTQIA+!",
"status": “CONCLUIDA” 
}

**Atualizar Ata Parcial**: localhost:8080/atas/id/
{
"pauta": "Filmes LGBTQIA+",
"assuntos": "Filmes LGBTQIA+ com personagens fortes",
"guilda":”ZuPride"
}

**Atualizar Membro Parcial**: localhost:8080/membros/id/
{
"nome": "Ana Carolina",
"email": "anacarolina@zup.com.br",
"zenity":”https://people.zup.com.br/",
“representante”:”false”
}

### CONTAMOS COM ISSUES E MELHORIAS

**DESENVOLVIDO POR:
- Íris Brito De Souza
- Natália Moneda Palota
- Ricardo Alcantara Silva

