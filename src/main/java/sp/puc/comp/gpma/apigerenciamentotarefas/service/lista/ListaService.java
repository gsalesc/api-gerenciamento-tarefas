package sp.puc.comp.gpma.apigerenciamentotarefas.service.lista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.Lista;
import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.dto.ListaCadastroDTO;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.ListaRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.repository.TarefaRepository;
import sp.puc.comp.gpma.apigerenciamentotarefas.service.lista.validations.ValidaLista;

@Service
public class ListaService {
	
	@Autowired
	private ListaRepository listaRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private List<ValidaLista> validarLista;
	
	
	public Lista adicionarLista(@Valid @RequestBody ListaCadastroDTO dados) {
		validarLista.forEach(lista -> lista.validar(dados));
		
		Lista lista = new Lista(dados);		
		listaRepository.save(lista);
		
		return lista;
	}
}
