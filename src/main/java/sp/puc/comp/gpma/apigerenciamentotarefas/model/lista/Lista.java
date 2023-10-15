package sp.puc.comp.gpma.apigerenciamentotarefas.model.lista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto.ListaCadastroDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;


@Entity
@Table
public class Lista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lista_tarefa")
	private Set<Tarefa> tarefas;
	
	@CreationTimestamp
	private LocalDateTime criadoEm;
	
	public Lista() {
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(criadoEm, id, tarefas, titulo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lista other = (Lista) obj;
		return Objects.equals(criadoEm, other.criadoEm) && Objects.equals(id, other.id)
				&& Objects.equals(tarefas, other.tarefas) && Objects.equals(titulo, other.titulo);
	}


	public Lista(String titulo) {
		this.titulo = titulo;
		this.tarefas = new HashSet<Tarefa>();
	}
	
	public Lista(ListaCadastroDTO dados) {
		this.titulo = dados.getTitulo();
		this.tarefas = new HashSet<Tarefa>();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTarefas(Set<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Set<Tarefa> getTarefas() {
		return tarefas;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}
}
