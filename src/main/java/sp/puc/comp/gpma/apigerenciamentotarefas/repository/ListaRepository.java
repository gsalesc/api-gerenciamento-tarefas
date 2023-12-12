package sp.puc.comp.gpma.apigerenciamentotarefas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.Lista;

public interface ListaRepository extends JpaRepository<Lista, Long> {
	Optional<Lista> findByTitulo(String titulo);
}
