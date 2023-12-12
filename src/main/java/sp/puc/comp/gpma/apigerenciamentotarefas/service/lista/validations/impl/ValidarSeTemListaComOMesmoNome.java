package sp.puc.comp.gpma.apigerenciamentotarefas.service.lista.validations.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sp.puc.comp.gpma.apigerenciamentotarefas.config.exception.ValidacaoException;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.Lista;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto.ListaCadastroDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.ListaRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.service.lista.validations.ValidaLista;

@Component
public class ValidarSeTemListaComOMesmoNome implements ValidaLista{

	@Autowired
	private ListaRepository listaRepository;
	
	@Override
	public void validar(ListaCadastroDTO dados) {
		Optional<Lista> op = listaRepository.findByTitulo(dados.getTitulo());
		
		if(op.isPresent()) {
			throw new ValidacaoException("Já existe uma lista com esse nome");
		}
	}

}
