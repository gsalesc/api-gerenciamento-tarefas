package sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tags.Tag;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaAtualizarDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaCadastroDTO;

@Entity
@Table
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataHora;
	
	@Enumerated
	private Status situacao;
	
	@ManyToMany
	@JoinTable(name = "tarefa_tag")
	//unidirecional -> classe dominante: tarefa
	//não queremos saber quais tarefa cada tag está ligada
	private List<Tag> tags;
	
	public Tarefa() {
	}
	
	public Tarefa(TarefaCadastroDTO dados) {
		this.titulo = dados.getTitulo();
		this.descricao = dados.getDescricao();
		this.dataHora = dados.getDataHora();
		this.situacao = dados.getSituacao();
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

	public void atualizar(TarefaAtualizarDTO dados) {
		this.titulo = dados.getTitulo();
		this.descricao = dados.getDescricao();
		this.dataHora = dados.getDataHora();
		this.situacao = dados.getSituacao();
	}
}
