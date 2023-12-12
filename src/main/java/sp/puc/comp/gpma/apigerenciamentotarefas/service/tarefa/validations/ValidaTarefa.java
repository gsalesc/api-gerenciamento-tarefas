package sp.puc.comp.gpma.apigerenciamentotarefas.service.tarefa.validations;

import sp.puc.comp.gpma.apigerenciamentotarefas.config.exception.ValidacaoException;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.tarefa.dto.TarefaCadastroDTO;

public interface ValidaTarefa {
	void validar(TarefaCadastroDTO dados) throws ValidacaoException; 
}
