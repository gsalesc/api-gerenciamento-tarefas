package sp.puc.comp.gpma.apigerenciamentotarefas.service.tarefa.validations.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import sp.puc.comp.gpma.apigerenciamentotarefas.config.exception.ValidacaoException;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaCadastroDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.service.tarefa.validations.ValidaTarefa;

@Component
public class ValidarSeADataJaPassou implements ValidaTarefa{

	@Override
	public void validar(TarefaCadastroDTO dados) {
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		
		if(dados.getDataHora().isBefore(dataHoraAtual)) {
			throw new ValidacaoException("A data marcada deve ser futura");
		}
	}

}
