package com.diel.tarefas.controller;

import com.diel.tarefas.domain.tarefa.AtualizaTarefaDTO;
import com.diel.tarefas.domain.tarefa.TarefaService;
import com.diel.tarefas.domain.tarefa.NovaTarefaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@SecurityRequirement(name = "bearer-key")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @Operation(summary = "Listar todas as tarefas", method = "GET")
    @GetMapping
    public ResponseEntity listarTarefas() {
        return ResponseEntity.ok(service.listarTarefas());
    }

    @Operation(summary = "Salvar uma nova tarefa", method = "POST")
    @PostMapping
    public ResponseEntity salvarTarefa(@RequestBody @Valid NovaTarefaDTO dados) {
        var dto = service.salvarTarefa(dados);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Atualizar uma tarefa", method = "PUT")
    @PutMapping
    public ResponseEntity atualizarTarefa(@RequestBody @Valid AtualizaTarefaDTO dados) {
        var dto = service.atualizarTarefa(dados);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Deletar uma tarefa", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity deletarTarefa(@PathVariable int id) {
        service.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }

}
