package sp.puc.comp.gpma.apigerenciamentotarefas.model.tags;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long descricao;
	
	public Long getId() {
		return id;
	}
	public Long getDescricao() {
		return descricao;
	}
}
