package sp.puc.comp.gpma.apigerenciamentotarefas.tarefa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaAtualizarDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AtualizaTarefaTest {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Test
	public void testeAtualizacao() {
		LocalDateTime data = LocalDateTime.now();
		Tarefa tarefa = new Tarefa("teste", "teste descricao", data, Status.AGENDADO);
	
		tarefaRepository.save(tarefa);
		
		Tarefa tarefaInserida = tarefaRepository.findById(1L).get();
		
		Assertions.assertThat(tarefaInserida).isNotNull();
		Assertions.assertThat(tarefaInserida.getId()).isEqualTo(1L);
		
		List<String> tags = new ArrayList<String>();
		tags.add("daisjifd");
		tags.add("asicj");
		TarefaAtualizarDTO tarefaAtualizada = new TarefaAtualizarDTO("teste atualizado", "teste descricao", data, Status.AGENDADO, tags);
		tarefaInserida.atualizar(tarefaAtualizada);
		tarefaRepository.save(tarefaInserida);
		
		Tarefa tarefaAtt = tarefaRepository.findById(1L).get();
		Assertions.assertThat(tarefaAtt).isNotNull();
		Assertions.assertThat(tarefaAtt.getTitulo()).isEqualTo("teste atualizado");
	}
}
