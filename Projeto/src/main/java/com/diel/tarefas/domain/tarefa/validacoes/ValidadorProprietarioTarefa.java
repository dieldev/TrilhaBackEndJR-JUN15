package com.diel.tarefas.domain.tarefa.validacoes;

import com.diel.tarefas.domain.ValidacaoException;
import com.diel.tarefas.domain.tarefa.Tarefa;
import com.diel.tarefas.domain.tarefa.TarefaRepository;
import com.diel.tarefas.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorProprietarioTarefa implements ValidadorTarefa {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public void validar(Tarefa tarefa, Usuario usuario) {
        if (!tarefaRepository.usuarioProprietarioExiste(tarefa.getId(), usuario)) {
            throw new ValidacaoException("Você não tem permissão para acessar essa tarefa!");
        }
    }
}
