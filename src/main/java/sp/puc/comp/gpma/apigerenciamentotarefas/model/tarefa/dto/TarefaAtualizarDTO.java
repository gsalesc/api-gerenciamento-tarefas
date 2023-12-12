package sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;

public class TarefaAtualizarDTO {
	
	private Long id;
	
	@NotNull
	@Size(max = 50)
	private String titulo;
	
	@NotNull
	@Size(max = 300)
	private String descricao;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataHora;
	
	@NotNull
	private Status situacao;
	
	private List<String> tags;
		
	public TarefaAtualizarDTO() {
	}
	
	public TarefaAtualizarDTO(@NotNull @Size(max = 50) String titulo, @NotNull @Size(max = 300) String descricao,
			@NotNull LocalDateTime dataHora, @NotNull Status situacao, List<String> tags) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataHora = dataHora;
		this.situacao = situacao;
		this.tags = tags;
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

	public List<String> getTags() {
		return tags;
	}
}
