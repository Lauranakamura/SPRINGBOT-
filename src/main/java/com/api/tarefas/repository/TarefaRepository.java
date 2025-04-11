package com.api.tarefas.repository;

import com.api.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // Consulta de tarefas completadas
    List<Tarefa> findByCompletadaTrue();
    
    // Consulta de tarefas não completadas
    List<Tarefa> findByCompletadaFalse();
}