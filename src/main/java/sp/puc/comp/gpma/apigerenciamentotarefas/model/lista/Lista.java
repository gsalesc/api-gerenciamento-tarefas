package sp.puc.comp.gpma.apigerenciamentotarefas.model.lista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany
	@JoinTable(name = "lista_tarefa")
	private List<Tarefa> tarefas;
	
	@CreationTimestamp
	private LocalDateTime criadoEm;
	
	public Lista(ListaCadastroDTO dados) {
		this.titulo = dados.getTitulo();
		this.tarefas = new ArrayList<Tarefa>();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}
}
