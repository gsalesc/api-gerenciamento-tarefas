package sp.puc.comp.gpma.apigerenciamentotarefas.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
	List<Tarefa> findByIdAndTitulo(Long id, String titulo);
	Tarefa findByTitulo(String titulo);
	
	@Query("select t from Tarefa t where t.dataHora = :dt")
	Optional<Tarefa> findByDataHora(LocalDateTime dt);
	
	Optional<List<Tarefa>> findBySituacao(Status st);
}
