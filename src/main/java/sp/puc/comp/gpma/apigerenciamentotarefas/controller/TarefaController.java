package sp.puc.comp.gpma.apigerenciamentotarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaAtualizarDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaCadastroDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaListagemDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.service.tarefa.TarefaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/tarefa")
@Tag(name = "Tarefa", description = "Operações para as tarefas")
public class TarefaController {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private TarefaService tarefaService;
	
	@PostMapping
	private ResponseEntity<TarefaListagemDTO> criarTarefa(@RequestBody TarefaCadastroDTO dados) {
		Tarefa tarefa = tarefaService.criarTarefa(dados);
		return ResponseEntity.ok(new TarefaListagemDTO(tarefa));
	}
	
	@GetMapping
	private List<TarefaListagemDTO> listarTarefas(){
		return tarefaService.listarTarefas().stream().map(TarefaListagemDTO::new).toList();
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<TarefaListagemDTO> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaAtualizarDTO dados) {
		return ResponseEntity.ok(new TarefaListagemDTO(tarefaService.atualizarTarefa(id, dados)));
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String> deletarTarefa(@PathVariable Long id) {
		tarefaService.deletarTarefa(id);
		return ResponseEntity.ok("Tarefa deletada");
	}
}
