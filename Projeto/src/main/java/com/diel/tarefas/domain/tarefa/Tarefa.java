package com.diel.tarefas.domain.tarefa;

import com.diel.tarefas.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tarefa;
    private LocalDateTime dataLancamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Tarefa(String tarefa, Usuario usuario) {
        this.tarefa = tarefa;
        this.dataLancamento = LocalDateTime.now();
        this.usuario = usuario;
    }
}
