package sp.puc.comp.gpma.apigerenciamentotarefas.lista;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.Lista;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.ListaRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ListaTest {

	@Autowired
	private ListaRepository listaRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Test
	public void criaLista() {
		Lista lista = new Lista("Nova lista");
		listaRepository.save(lista);
		
		Assertions.assertThat(listaRepository.findAll()).isNotNull();
		Assertions.assertThat(listaRepository.findAll()).isNotEmpty();
	}
	
	@Test
	public void adicionaNaLista() {
		LocalDateTime data = LocalDateTime.now();
		Tarefa tarefa = new Tarefa("teste", "teste descricao", data, Status.AGENDADO);
		
		tarefaRepository.save(tarefa);
		
		if(listaRepository.findAll().size() > 0) {
			Lista lista = new Lista("Nova lista");
			listaRepository.save(lista);
			
			lista.getTarefas().add(tarefa);
			listaRepository.save(lista);

			Assertions.assertThat(lista.getTarefas()).isNotNull();
			Assertions.assertThat(lista.getTarefas()).isNotEmpty();
		}
	}
	
	@Test
	public void deletaNaLista() {
		LocalDateTime data = LocalDateTime.now();
		Tarefa tarefa = new Tarefa("teste1", "teste descricao", data, Status.AGENDADO);
		Tarefa tarefa2 = new Tarefa("teste1", "teste descricao", data, Status.AGENDADO);
		Tarefa tarefa3 = new Tarefa("teste1", "teste descricao", data, Status.AGENDADO);

		tarefaRepository.save(tarefa);
		tarefaRepository.save(tarefa2);
		tarefaRepository.save(tarefa3);
		
		if(listaRepository.findAll().size() > 0) {
			Lista lista = new Lista("Nova lista");
			listaRepository.save(lista);
			
			lista.getTarefas().add(tarefa);
			lista.getTarefas().add(tarefa2);
			lista.getTarefas().add(tarefa3);
			listaRepository.save(lista);

			int tam = lista.getTarefas().size();
			Assertions.assertThat(lista.getTarefas()).isNotNull();
			Assertions.assertThat(lista.getTarefas()).isNotEmpty();
			
			Lista nova = listaRepository.findById(1L).get();
			
			nova.getTarefas().remove(tarefa2);
			listaRepository.save(nova);
						
			Assertions.assertThat(nova.getTarefas().size()).isLessThan(tam);
		}
	}
}
