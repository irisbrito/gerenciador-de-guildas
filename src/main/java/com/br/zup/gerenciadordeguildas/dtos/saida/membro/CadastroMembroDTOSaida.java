package com.br.zup.gerenciadordeguildas.dtos.saida.membro;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroMembroDTOSaida {
        private Integer id;
        private String nome;
        private String email;
        private String zenity;
        private boolean representante;
        private Guilda guilda;

        public static CadastroMembroDTOSaida converterEntityParaDTOSaida(Membro membro) {
            CadastroMembroDTOSaida cadastroMembroDTOSaida = new CadastroMembroDTOSaida();
            cadastroMembroDTOSaida.setId(membro.getId());
            cadastroMembroDTOSaida.setNome(membro.getNome());
            cadastroMembroDTOSaida.setEmail(membro.getEmail());
            cadastroMembroDTOSaida.setZenity(membro.getZenity());
            cadastroMembroDTOSaida.setRepresentante(membro.isRepresentante());
            cadastroMembroDTOSaida.setGuilda(membro.getGuilda());

            return cadastroMembroDTOSaida;
        }
}
