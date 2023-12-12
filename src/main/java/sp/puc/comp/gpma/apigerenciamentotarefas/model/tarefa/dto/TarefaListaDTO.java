package sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;

public class TarefaListaDTO {
	
	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataHora;
	
	private Status situacao;

	public TarefaListaDTO() {
		
	}
	
	public TarefaListaDTO(Tarefa tarefa) {
		this.id = tarefa.getId();
		this.titulo = tarefa.getTitulo();
		this.descricao = tarefa.getDescricao();
		this.dataHora = tarefa.getDataHora();
		this.situacao = tarefa.getSituacao();
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
}
