package sp.puc.comp.gpma.apigerenciamentotarefas.service.tarefa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import sp.puc.comp.gpma.apigerenciamentotarefas.config.exception.ValidacaoException;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tags.Tag;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Status;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaAtualizarDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaCadastroDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TagsRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.service.tarefa.validations.ValidaTarefa;

@Service
public class TarefaService {
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private TagsRepository tagsRepository;
	
	@Autowired
	private List<ValidaTarefa> validarTarefa;
	
	public Tarefa criarTarefa(@RequestBody TarefaCadastroDTO dados) {
		validarTarefa.forEach(tr -> tr.validar(dados));

		Tarefa tarefa = new Tarefa(dados);
				
		for(String tag : dados.getTags()) {
			if(tagsRepository.existsByDescricao(tag)) {
				Tag existe = tagsRepository.findByDescricao(tag);
				tarefa.getTags().add(existe);
			} else {
				Tag tg = new Tag(tag);
				tagsRepository.save(tg);
				tarefa.getTags().add(tg);
			}
		}
		
		tarefaRepository.save(tarefa);
		return tarefa;
	}
	
	public List<Tarefa> listarTarefas(){
		this.atualizarAtrasados();
		return tarefaRepository.findAll();
	}
	
	public Tarefa atualizarTarefa(Long id, @RequestBody TarefaAtualizarDTO dados) {
		Tarefa tarefa = tarefaRepository.findById(id).get();
		tarefa.atualizar(dados);
		
		for(String tag : dados.getTags()) {
			if(!tagsRepository.existsByDescricao(tag)) {
				Tag tg = new Tag(tag);
				tagsRepository.save(tg);
				tarefa.getTags().add(tg);
			}
		}
		
		tarefaRepository.save(tarefa);
		return tarefa;
	}
	
	public String deletarTarefa(Long id) {
		Tarefa tarefa = tarefaRepository.findById(id).get();
		tarefaRepository.delete(tarefa);
		
		return "Tarefa deletada";
	}
	
	public void atualizarAtrasados() {
		List<Tarefa> tarefas = this.tarefaRepository.findBySituacao(Status.AGENDADO)
													.orElseThrow(() -> {throw new ValidacaoException("");});
		
		for(Tarefa t : tarefas) {
			LocalDateTime agora = LocalDateTime.now();
			
			if(t.getDataHora().isBefore(agora)) {
				t.setSituacao(Status.ATRASADO);
				
				this.tarefaRepository.save(t);
			}
		}
	}
}
