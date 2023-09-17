package sp.puc.comp.gpma.apigerenciamentotarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

}
