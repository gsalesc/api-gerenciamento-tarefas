package sp.puc.comp.gpma.apigerenciamentotarefas.service.tarefa.validations.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sp.puc.comp.gpma.apigerenciamentotarefas.config.exception.ValidacaoException;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.Tarefa;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaCadastroDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.service.tarefa.validations.ValidaTarefa;

@Component
public class ValidarSeJaTemTarefaNaMesmaDataEHora implements ValidaTarefa{

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Override
	public void validar(TarefaCadastroDTO dados) throws ValidacaoException {
		Optional<Tarefa> op = tarefaRepository.findByDataHora(dados.getDataHora());
		
		if(op.isPresent()) {
			throw new ValidacaoException("Já existe uma tarefa nessa data e hora");
		}
	}

}
