package com.diel.tarefas.domain.tarefa;

import com.diel.tarefas.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    @Query("SELECT t FROM Tarefa t WHERE t.usuario = :usuario")
    List<Tarefa> listarTarefas(Usuario usuario);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Tarefa t WHERE t.id = :idTarefa and t.usuario = :usuario")
    boolean usuarioProprietarioExiste(int idTarefa, Usuario usuario);
}
