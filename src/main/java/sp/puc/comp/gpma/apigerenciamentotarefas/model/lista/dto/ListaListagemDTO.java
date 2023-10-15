package sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.Lista;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaListaDTO;

public class ListaListagemDTO {
	private Long id;
	private String titulo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime criadoEm;
	private List<TarefaListaDTO> tarefas;
	
	public ListaListagemDTO() {
	}
	
	public ListaListagemDTO(Lista lista) {
		this.id = lista.getId();
		this.titulo = lista.getTitulo();
		this.criadoEm = lista.getCriadoEm();
		this.tarefas = new ArrayList<TarefaListaDTO>();
		
		for(Tarefa tarefa : lista.getTarefas()) {
			this.tarefas.add(new TarefaListaDTO(tarefa));
		}
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}
	public List<TarefaListaDTO> getTarefas() {
		return tarefas;
	}
}
