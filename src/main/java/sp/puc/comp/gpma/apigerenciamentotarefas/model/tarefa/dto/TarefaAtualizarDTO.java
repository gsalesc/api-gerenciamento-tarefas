package sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tags.Tag;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;

public class TarefaAtualizarDTO {
	
	
	@NotNull
	@NotBlank
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
	
	private List<Tag> tags;
	
	public TarefaAtualizarDTO(@NotNull @NotBlank Long id, @NotNull @Size(max = 50) String titulo, @NotNull @Size(max = 300) String descricao,
			@NotNull LocalDateTime dataHora, @NotNull Status situacao) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataHora = dataHora;
		this.situacao = situacao;
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

	public List<Tag> getTags() {
		return tags;
	}
}
