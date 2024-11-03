package com.diel.tarefas.domain.tarefa;

import com.diel.tarefas.domain.ValidacaoException;
import com.diel.tarefas.domain.tarefa.validacoes.ValidadorTarefa;
import com.diel.tarefas.domain.usuario.Usuario;
import com.diel.tarefas.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorTarefa> validadoresTarefas;

    public List<TarefaDetalhadaDTO> listarTarefas() {
        var nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        var usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        List<Tarefa> tarefas = tarefaRepository.listarTarefas((Usuario) usuario);

        return tarefas.stream().map(t -> new TarefaDetalhadaDTO(t.getId(), t.getTarefa(), t.getDataLancamento(), t.getUsuario().getNomeUsuario())).toList();
    }

    public NovaTarefaSalva salvarTarefa(NovaTarefaDTO dados) {
        var nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!usuarioRepository.existsByNomeUsuario(nomeUsuario)) {
            throw new ValidacaoException("Ocorreu um erro ao tentar localizar o seu usuário!");
        }
        var usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);

        Tarefa tarefa = new Tarefa(dados.tarefa(), (Usuario) usuario);
        tarefaRepository.save(tarefa);
        return new NovaTarefaSalva(tarefa.getId(), tarefa.getTarefa(), tarefa.getDataLancamento());
    }

    public NovaTarefaSalva atualizarTarefa(AtualizaTarefaDTO dados) {
        if (!tarefaRepository.existsById(dados.id())) {
            throw new ValidacaoException("Não foi possível localizar essa tarefa!");
        }
        var nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        var usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        var tarefa = tarefaRepository.findById(dados.id()).get();

        validadoresTarefas.forEach(v -> v.validar(tarefa, (Usuario) usuario));

        tarefa.setTarefa(dados.tarefa());
        tarefaRepository.save(tarefa);

        return new NovaTarefaSalva(tarefa.getId(), tarefa.getTarefa(), tarefa.getDataLancamento());
    }

    public void deletarTarefa(int idTarefa) {
        if (!tarefaRepository.existsById(idTarefa)) {
            throw new ValidacaoException("Não foi possível localizar essa tarefa!");
        }
        var nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        var usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        var tarefa = tarefaRepository.findById(idTarefa).get();

        validadoresTarefas.forEach(v -> v.validar(tarefa, (Usuario) usuario));

        tarefaRepository.delete(tarefa);
    }
}
