package br.com.eduardo.spring.arquitetura.arquiteturaspring.service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.abstractUtil.AbstractModel;

public abstract class CrudServiceImpl<E extends AbstractModel> implements ICrudService<E> {

    @Override
    public E save(E entity) {
        return getRepository().save(entity);
    }

	public Page<E> findPaged(Pageable pageable){
		return getRepository().findAll(pageable);
	}

	@Override
	public List<E> findAll() {
		return getRepository().findAll();
	}

	@Override
	public Optional<E> findById(Long id) {
		return getRepository().findById(id);
	}

	@Override
	public E update(E model) {
		if(Objects.isNull(model.getId())) {
			throw new RuntimeException("Entidade " + model + "sem ID para atualizacao");
		}
		return getRepository().save(model);
	}

	@Override
	public void delete(Long id) {
		getRepository().deleteById(id);
	}
    public abstract JpaRepository<E, Long> getRepository();
    
}
