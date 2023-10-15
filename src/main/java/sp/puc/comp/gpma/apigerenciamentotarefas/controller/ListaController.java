package sp.puc.comp.gpma.apigerenciamentotarefas.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.Lista;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto.ListaCadastroDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto.ListaListagemDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto.ListaOperacaoTarefaDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.ListaRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;

@RestController
@RequestMapping("/api/lista")
public class ListaController {
	
	@Autowired
	private ListaRepository listaRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@PostMapping
	public void adicionarLista(@RequestBody ListaCadastroDTO dados) {
		Lista lista = new Lista(dados);		
		listaRepository.save(lista);
	}
	
	@PostMapping("/{id}")
	public void adicionarTarefas(@PathVariable Long id, @RequestBody ListaOperacaoTarefaDTO dados) {
	
		Optional<Lista> op = listaRepository.findById(id);
		
		if(op.isPresent()) {
			Lista lista = op.get();
			
			for(Long tarefa_id : dados.getTarefas_id()) {
				if(tarefaRepository.existsById(tarefa_id)) {
					Tarefa tarefa = tarefaRepository.findById(tarefa_id).get();
										
					Tarefa repetida = getTarefaPorId(lista, tarefa_id);
					
					if(repetida == null) {
						lista.getTarefas().add(tarefa);	
						listaRepository.save(lista);
					}
				}
			}
		}
		
	}
	
	@DeleteMapping("/{id}")
	public void deletarTarefas(@PathVariable Long id, @RequestBody ListaOperacaoTarefaDTO dados) {
		Optional<Lista> op = listaRepository.findById(id);
		
		if(op.isPresent()) {
			Lista lista = op.get();
			for(Long tarefa_id : dados.getTarefas_id()) {
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
		}
		
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
}