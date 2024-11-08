package com.diel.tarefas.domain.tarefa;

import com.diel.tarefas.domain.ValidacaoException;
import com.diel.tarefas.domain.tarefa.mapper.TarefaMapper;
import com.diel.tarefas.domain.tarefa.validacoes.ValidadorTarefa;
import com.diel.tarefas.domain.tarefa.validacoes.ValidadorTarefaComposite;
import com.diel.tarefas.domain.usuario.Usuario;
import com.diel.tarefas.domain.usuario.UsuarioRepository;
import com.diel.tarefas.infra.auth.AuthenticationHelper;
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
    private ValidadorTarefaComposite validadorTarefaComposite;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    public List<TarefaDetalhadaDTO> listarTarefas() {
        var usuario = authenticationHelper.getAuthenticatedUser();
        List<Tarefa> tarefas = tarefaRepository.listarTarefas(usuario);

        return tarefas.stream().map(TarefaMapper::toTarefaDetalhadaDTO).toList();
    }

    public NovaTarefaSalva salvarTarefa(NovaTarefaDTO dados) {
        var nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!usuarioRepository.existsByNomeUsuario(nomeUsuario)) {
            throw new ValidacaoException("Ocorreu um erro ao tentar localizar o seu usuário!");
        }
        var usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);

        Tarefa tarefa = TarefaMapper.toTarefa(dados, usuario);
        tarefaRepository.save(tarefa);
        return TarefaMapper.toNovaTarefaSalva(tarefa);
    }

    public NovaTarefaSalva atualizarTarefa(AtualizaTarefaDTO dados) {
        if (!tarefaRepository.existsById(dados.id())) {
            throw new ValidacaoException("Não foi possível localizar essa tarefa!");
        }
        var usuario = authenticationHelper.getAuthenticatedUser();
        var tarefa = tarefaRepository.findById(dados.id()).get();

        validadorTarefaComposite.validar(tarefa);

        tarefa.setTarefa(dados.tarefa());
        tarefaRepository.save(tarefa);

        return TarefaMapper.toNovaTarefaSalva(tarefa);
    }

    public void deletarTarefa(int idTarefa) {
        if (!tarefaRepository.existsById(idTarefa)) {
            throw new ValidacaoException("Não foi possível localizar essa tarefa!");
        }
        var usuario = authenticationHelper.getAuthenticatedUser();
        var tarefa = tarefaRepository.findById(idTarefa).get();

        validadorTarefaComposite.validar(tarefa);

        tarefaRepository.delete(tarefa);
    }
}
