package sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ListaCadastroDTO {
	
	@NotNull
	@NotEmpty
	@Size(max = 50)
	private String titulo;
	
	private List<Long> tarefas_id;
	
	public ListaCadastroDTO() {
	}
	
	public String getTitulo() {
		return titulo;
	}
	public List<Long> getTarefas_id() {
		return tarefas_id;
	}
}
