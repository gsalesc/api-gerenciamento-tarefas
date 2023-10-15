package sp.puc.comp.gpma.apigerenciamentotarefas;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;

@SpringBootApplication
public class ApiGerenciamentoTarefasApplication {

	@Autowired
	private static TarefaRepository tarefaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiGerenciamentoTarefasApplication.class, args);
		//atualizarTarefaAtrasada();
	}

	private static void atualizarTarefaAtrasada() {
		List<Tarefa> todasAsTarefas = tarefaRepository.findAll();
		LocalDateTime dataAtual = LocalDateTime.now();
		
		for(Tarefa tarefa : todasAsTarefas) {
			if(tarefa.getDataHora().isBefore(dataAtual) && tarefa.getSituacao() == Status.AGENDADO) {
				tarefa.setSituacao(Status.ATRASADO);
			}
		}
	}
}
