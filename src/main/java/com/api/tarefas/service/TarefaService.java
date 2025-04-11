package com.api.tarefas.service;

import com.api.tarefas.model.Tarefa;
import com.api.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    // Criar/Atualizar Tarefa
    public Tarefa salvarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Buscar Tarefa por ID
    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    // Atualizar Tarefa (apenas descrição e completada)
    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        return tarefaRepository.findById(id)
            .map(tarefaExistente -> {
                tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
                tarefaExistente.setCompletada(tarefaAtualizada.isCompletada());
                return tarefaRepository.save(tarefaExistente);
            })
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    // Excluir Tarefa
    public void excluirTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    // Listar Tarefas Completadas
    public List<Tarefa> listarTarefasCompletadas() {
        return tarefaRepository.findByCompletadaTrue();
    }

    // Listar Tarefas Não Completadas
    public List<Tarefa> listarTarefasNaoCompletadas() {
        return tarefaRepository.findByCompletadaFalse();
    }
}