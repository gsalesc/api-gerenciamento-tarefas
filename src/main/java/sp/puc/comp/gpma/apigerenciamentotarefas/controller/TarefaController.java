package sp.puc.comp.gpma.apigerenciamentotarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.tags.Tag;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaAtualizarDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaCadastroDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaListagemDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TagsRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;

@RestController
@RequestMapping("api/tarefa")
public class TarefaController {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private TagsRepository tagsRepository;
	
	@PostMapping
	private ResponseEntity<TarefaListagemDTO> criarTarefa(@RequestBody TarefaCadastroDTO dados) {
		Tarefa tarefa = new Tarefa(dados);
		
		for(String tag : dados.getTags()) {
			if(tagsRepository.existsByDescricao(tag)) {
				Tag existe = tagsRepository.findByDescricao(tag);
				tarefa.getTags().add(existe);
			} else {
				Tag tg = new Tag(tag);
				tagsRepository.save(tg);
				tarefa.getTags().add(tg);
			}
		}
				
		tarefaRepository.save(tarefa);
		return ResponseEntity.ok(new TarefaListagemDTO(tarefa));
	}
	
	@GetMapping
	private List<TarefaListagemDTO> listarTarefas(){
		return tarefaRepository.findAll().stream().map(TarefaListagemDTO::new).toList();
	}
	
	@PutMapping("/{id}")
	private void atualizarTarefa(@PathVariable Long id, @RequestBody TarefaAtualizarDTO dados) {
		Tarefa tarefa = tarefaRepository.findById(id).get();
		tarefa.atualizar(dados);
		
		for(String tag : dados.getTags()) {
			if(!tagsRepository.existsByDescricao(tag)) {
				Tag tg = new Tag(tag);
				tagsRepository.save(tg);
				tarefa.getTags().add(tg);
			}
		}
		
		tarefaRepository.save(tarefa);
	}
	
	@DeleteMapping("/{id}")
	private void deletarTarefa(@PathVariable Long id) {
		Tarefa tarefa = tarefaRepository.findById(id).get();
		tarefaRepository.delete(tarefa);
	}
}
