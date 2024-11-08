package com.diel.tarefas.domain.tarefa.mapper;

import com.diel.tarefas.domain.tarefa.NovaTarefaDTO;
import com.diel.tarefas.domain.tarefa.NovaTarefaSalva;
import com.diel.tarefas.domain.tarefa.Tarefa;
import com.diel.tarefas.domain.tarefa.TarefaDetalhadaDTO;
import com.diel.tarefas.domain.usuario.Usuario;

import java.time.LocalDateTime;

public class TarefaMapper {

    // Método para convertar Tarefa em TarefaDetalhadoDTO
    public static TarefaDetalhadaDTO toTarefaDetalhadaDTO(Tarefa tarefa) {
        return new TarefaDetalhadaDTO(
                tarefa.getId(),
                tarefa.getTarefa(),
                tarefa.getDataLancamento(),
                tarefa.getUsuario().getNomeUsuario()
        );
    }

    // Método para converter NovaTarefaDTO em Tarefa
    public static Tarefa toTarefa(NovaTarefaDTO novaTarefaDTO, Usuario usuario) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTarefa(novaTarefaDTO.tarefa());
        tarefa.setUsuario(usuario);
        tarefa.setDataLancamento(LocalDateTime.now());
        return tarefa;
    }

    // Método para converter Tarefa em NovaTarefaSalva
    public static NovaTarefaSalva toNovaTarefaSalva(Tarefa tarefa) {
        return new NovaTarefaSalva(
                tarefa.getId(),
                tarefa.getTarefa(),
                tarefa.getDataLancamento()
        );
    }
}
