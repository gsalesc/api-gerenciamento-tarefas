package sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.tags.Tag;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;

public class TarefaListagemDTO {
	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataHora;
	
	private Status situacao;
	
	private List<String> tags; 
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	//private LocalDateTime criadoEm;
	
	public TarefaListagemDTO() {
	}
	
	public TarefaListagemDTO(Tarefa tarefa) {
		this.id = tarefa.getId();
		this.titulo = tarefa.getTitulo();
		this.descricao = tarefa.getDescricao();
		this.dataHora = tarefa.getDataHora();
		this.situacao = tarefa.getSituacao();
		this.tags = new ArrayList<String>();
		
		for(Tag tags : tarefa.getTags()) {
			this.tags.add(tags.getDescricao());
		}
	}
	
	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public Status getSituacao() {
		return situacao;
	}
	
	public List<String> getTags(){
		return tags;
	}

	/*public LocalDateTime getCriadoEm() {
		return criadoEm;
	}*/
}
