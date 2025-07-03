package com.rodrigo.votingagenda.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public enum Votes {
    YES("sim"),
    NO("não");

    private final String descricao;

    public static Votes fromString(String valor) {
        for (Votes voto : Votes.values()) {
            if (voto.name().equalsIgnoreCase(valor) ||
                    voto.getDescricao().equalsIgnoreCase(valor)) {
                return voto;
            }
        }
        throw new IllegalArgumentException("Voto inválido: " + valor);
    }

}
