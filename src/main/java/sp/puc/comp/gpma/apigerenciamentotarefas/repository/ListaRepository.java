package sp.puc.comp.gpma.apigerenciamentotarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.lista.Lista;

public interface ListaRepository extends JpaRepository<Lista, Long> {

}
