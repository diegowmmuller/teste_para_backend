package br.backend;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Entidade {
    PRODUTO,
    CATEGORIA;

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }
}
