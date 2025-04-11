package com.api.tarefas.controller;

import com.api.tarefas.model.Tarefa;
import com.api.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    // Criar Tarefa
    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.salvarTarefa(tarefa);
    }

    // Atualizar Tarefa (apenas descrição e completada)
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(
        @PathVariable Long id, 
        @RequestBody Tarefa tarefa
    ) {
        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, tarefa);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    // Excluir Tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }

    // Listar Tarefas Completadas
    @GetMapping("/completadas")
    public List<Tarefa> listarTarefasCompletadas() {
        return tarefaService.listarTarefasCompletadas();
    }

    // Listar Tarefas Não Completadas
    @GetMapping("/pendentes")
    public List<Tarefa> listarTarefasNaoCompletadas() {
        return tarefaService.listarTarefasNaoCompletadas();
    }
}