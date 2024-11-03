package com.diel.tarefas.domain.tarefa;

import jakarta.validation.constraints.NotBlank;

public record NovaTarefaDTO(@NotBlank(message = "A tarefa não pode ser nula!") String tarefa) {
}
