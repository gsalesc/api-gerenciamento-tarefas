package sp.puc.comp.gpma.apigerenciamentotarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.Lista;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto.ListaOperacaoTarefaDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto.ListaCadastroDTO;
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
		
		for(Long id : dados.getTarefas_id()) {
			if(tarefaRepository.existsById(id)) {
				lista.getTarefas().add(tarefaRepository.findById(id).get());
			}
		}
		
		listaRepository.save(lista);
	}
	
	@PostMapping("/@{id}")
	public void adicionarTarefas(@PathVariable Long id, @RequestBody ListaOperacaoTarefaDTO dados) {
		for(Long tarefa_id : dados.getTarefas_id()) {
			
			if(listaRepository.existsById(id)) {
				if(tarefaRepository.existsById(tarefa_id)) {
					Lista lista = listaRepository.findById(id).get();
					Tarefa tarefa = tarefaRepository.findById(tarefa_id).get();
					
					lista.getTarefas().add(tarefa);	
				}
			}
		}
	}
	
	@DeleteMapping("/@{id}")
	public void deletarTarefas(@PathVariable Long id, @RequestBody ListaOperacaoTarefaDTO dados) {
		for(Long tarefa_id : dados.getTarefas_id()) {
			
			if(listaRepository.existsById(id)) {
				if(tarefaRepository.existsById(tarefa_id)) {
					Lista lista = listaRepository.findById(id).get();
					Tarefa tarefa = tarefaRepository.findById(tarefa_id).get();
					
					if(lista.getTarefas().contains(tarefa)) {
						lista.getTarefas().remove(tarefa);
					}
				}
			}
		}
	}
		
	@GetMapping
	public List<Lista> listarLista(){
		return listaRepository.findAll();
	}
}
