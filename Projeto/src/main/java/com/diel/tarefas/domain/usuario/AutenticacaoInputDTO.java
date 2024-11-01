package com.diel.tarefas.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoInputDTO(
        @NotBlank(message = "O campo usuário é obrigatório!")
        String usuario,
        @NotBlank(message = "O campo senha é obrigatório!")
        String senha) {
}