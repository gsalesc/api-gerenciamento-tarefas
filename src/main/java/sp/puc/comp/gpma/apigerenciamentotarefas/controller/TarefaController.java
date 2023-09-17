package sp.puc.comp.gpma.apigerenciamentotarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaCadastroDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaListagemDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;

@RestController
@RequestMapping("api/tarefa")
public class TarefaController {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@PostMapping
	private void criarTarefa(@RequestBody TarefaCadastroDTO dados) {
		Tarefa tarefa = new Tarefa(dados);
		tarefaRepository.save(tarefa);
	}
	
	@GetMapping
	private List<TarefaListagemDTO> listarTarefas(){
		return tarefaRepository.findAll().stream().map(TarefaListagemDTO::new).toList();
	}

}
