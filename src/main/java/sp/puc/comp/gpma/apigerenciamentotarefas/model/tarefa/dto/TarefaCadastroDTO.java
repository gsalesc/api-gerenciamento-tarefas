package sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tags.Tag;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;

public class TarefaCadastroDTO {
	
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
