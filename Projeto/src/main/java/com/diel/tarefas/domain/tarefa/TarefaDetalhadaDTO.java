package com.diel.tarefas.domain.tarefa;

import java.time.LocalDateTime;

public record TarefaDetalhadaDTO(int id, String tarefa, LocalDateTime data, String usuario) {
}
