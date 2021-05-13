package com.br.zup.gerenciadordeguildas.dtos.saida.membro;

import com.br.zup.gerenciadordeguildas.dtos.saida.guilda.GuildaSaidaDTO;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import lombok.*;


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
        private GuildaSaidaDTO guilda;

        public static CadastroMembroDTOSaida converterEntityParaDTOSaida(Membro membro) {
            CadastroMembroDTOSaida cadastroMembroDTOSaida = new CadastroMembroDTOSaida();
            cadastroMembroDTOSaida.setId(membro.getId());
            cadastroMembroDTOSaida.setNome(membro.getNome());
            cadastroMembroDTOSaida.setEmail(membro.getEmail());
            cadastroMembroDTOSaida.setZenity(membro.getZenity());
            cadastroMembroDTOSaida.setRepresentante(membro.isRepresentante());
            if(membro.getGuilda() != null) {
                cadastroMembroDTOSaida.setGuilda(GuildaSaidaDTO.converterGuildaParaDTO(membro.getGuilda()));
            }
            return cadastroMembroDTOSaida;
        }
}
