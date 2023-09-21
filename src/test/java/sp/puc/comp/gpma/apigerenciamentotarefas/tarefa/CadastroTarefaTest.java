package sp.puc.comp.gpma.apigerenciamentotarefas.tarefa;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.inject.Scope;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CadastroTarefaTest {
	
	@Autowired
	private TarefaRepository tarefaRepository;
		
	@Test
	public void testeCadastro() {
		LocalDateTime data = LocalDateTime.now();
		Tarefa tarefa = new Tarefa("teste", "teste descricao", data, Status.AGENDADO);
		
		tarefaRepository.save(tarefa);
		
		Tarefa tarefaInserida = tarefaRepository.findById(1L).get();
		
		Assertions.assertThat(tarefaInserida).isNotNull();
		Assertions.assertThat(tarefaInserida.getId()).isEqualTo(1L);
	}
}
