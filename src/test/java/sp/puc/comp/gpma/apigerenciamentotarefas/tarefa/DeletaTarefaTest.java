package sp.puc.comp.gpma.apigerenciamentotarefas.tarefa;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeletaTarefaTest {
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Test
	public void testeDelecao() {	
		LocalDateTime data = LocalDateTime.now();
		Tarefa tarefa = new Tarefa("teste", "teste descricao", data, Status.AGENDADO);
		tarefaRepository.save(tarefa);
		
		List<Tarefa> tarefas = tarefaRepository.findAll();
		int tamanhoInicial = tarefas.size();
		
		Tarefa tarefaInserida = tarefaRepository.findById(1L).get();
		tarefaRepository.delete(tarefaInserida);
		
		tarefas = tarefaRepository.findAll();
		
		Assertions.assertThat(tarefas).isNotNull();
		Assertions.assertThat(tarefas.size()).isEqualTo(tamanhoInicial-1);
	}
}