package sp.puc.comp.gpma.apigerenciamentotarefas.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import io.swagger.v3.oas.annotations.tags.Tag;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.Lista;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto.ListaCadastroDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto.ListaListagemDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto.ListaOperacaoTarefaDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.ListaRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.service.lista.ListaService;

@RestController
@RequestMapping("/api/lista")
@Tag(name = "Lista", description = "Operações para as listas")
public class ListaController {
	
	@Autowired
	private ListaRepository listaRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private ListaService listaService;
	
	@PostMapping
	public ResponseEntity<ListaListagemDTO> adicionarLista(@RequestBody ListaCadastroDTO dados) {
		return ResponseEntity.ok(new ListaListagemDTO(listaService.adicionarLista(dados)));
	}
	
	@PutMapping("/adicionarTarefa/{id}")
	public ResponseEntity<ListaListagemDTO> adicionarTarefas(@PathVariable Long id, @RequestBody ListaOperacaoTarefaDTO dados) {
	
		Optional<Lista> op = listaRepository.findById(id);
		Lista lista = null; 
		if(op.isPresent()) {
			lista = op.get();
				Long idTarefa = dados.getTarefas_id();
				if(tarefaRepository.existsById(id)) {
					Tarefa tarefa = tarefaRepository.findById(idTarefa).get();
										
					Tarefa repetida = getTarefaPorId(lista, idTarefa);
					
					if(repetida == null) {
						lista.getTarefas().add(tarefa);	
						listaRepository.save(lista);
					}
				}
			
		} else {System.out.println("ksjcwevunerivrel");}
		
		return ResponseEntity.ok(new ListaListagemDTO(lista));
	}
	
	@PutMapping("/deletarTarefa/{id}")
	public ResponseEntity<ListaListagemDTO> deletarTarefas(@PathVariable Long id, @RequestBody ListaOperacaoTarefaDTO dados) {
		Optional<Lista> op = listaRepository.findById(id);
		Lista lista = null;
		if(op.isPresent()) {
			
			lista = op.get();
			Long tarefa_id = dados.getTarefas_id();
				if(tarefaRepository.existsById(tarefa_id)) {
					Tarefa tarefa = tarefaRepository.findById(id).get();
					
					Set<Tarefa> tarefas = lista.getTarefas();
					Tarefa remover = getTarefaPorId(lista, tarefa_id);
					
					if(tarefa != null) {
						tarefas.remove(remover);
						lista.setTarefas(tarefas);
						listaRepository.save(lista);
					}
				}
			
		}
		
		return ResponseEntity.ok(new ListaListagemDTO(lista));
	}
	
	private Tarefa getTarefaPorId(Lista lista, Long id) {
		Tarefa tar = null;
		
		for(Tarefa t : lista.getTarefas()) {
			if(t.getId() == id) {
				tar = t;
			}
		}
		
		return tar;
	}
		
	@GetMapping
	public List<ListaListagemDTO> listarLista(){
		return listaRepository.findAll().stream().map(ListaListagemDTO::new).toList();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarLista(@PathVariable Long id){
		Lista lista = listaRepository.findById(id).get();
		listaRepository.delete(lista);
		
		return ResponseEntity.ok("Lista deletada");
	}
}