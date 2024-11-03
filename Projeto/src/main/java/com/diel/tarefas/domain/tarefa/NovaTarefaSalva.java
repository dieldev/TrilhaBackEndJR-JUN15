package com.diel.tarefas.domain.tarefa;

import java.time.LocalDateTime;
import java.util.Date;

public record NovaTarefaSalva(int id, String tarefa, LocalDateTime data) {
}
