package sp.puc.comp.gpma.apigerenciamentotarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sp.puc.comp.gpma.apigerenciamentotarefas.model.tags.Tag;

public interface TagsRepository extends JpaRepository<Tag, Long>{
	Tag findByDescricao(String desc);
	boolean existsByDescricao(String desc);
}
