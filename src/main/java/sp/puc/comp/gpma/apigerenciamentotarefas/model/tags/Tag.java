package sp.puc.comp.gpma.apigerenciamentotarefas.model.tags;

import jakarta.persistence.Column;
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
	
	@Column(unique = true)
	private String descricao;
	
	public Tag() {
	}
	
	public Tag(String descricao) {
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}	
}
