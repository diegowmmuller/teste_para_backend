package br.backend;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Acao {
    CRIAR,
    ENCONTRAR,
    ATUALIZAR,
    DELETAR,
    LISTAR;

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }
}
