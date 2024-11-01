package com.diel.tarefas.controller;

import com.diel.tarefas.domain.tarefa.novaTarefaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @GetMapping
    public ResponseEntity listarTarefas() {
        String loginUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok("Olá " + loginUsuario + "!");
    }

    @PostMapping
    public ResponseEntity salvarTarefas(@RequestBody novaTarefaDTO dados) {
        return ResponseEntity.ok("");
    }
}
