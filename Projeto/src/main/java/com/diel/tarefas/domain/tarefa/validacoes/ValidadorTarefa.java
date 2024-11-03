package com.diel.tarefas.domain.tarefa.validacoes;

import com.diel.tarefas.domain.tarefa.Tarefa;
import com.diel.tarefas.domain.usuario.Usuario;

public interface ValidadorTarefa {

    void validar(Tarefa tarefa, Usuario usuario);
}
