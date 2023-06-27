package br.com.eduardo.spring.arquitetura.arquiteturaspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.abstractUtil.AbstractModel;

public interface ICrudService<E extends AbstractModel> {
    
    E save(E entity);

    public List<E> findAll();
	
	public Optional<E> findById(Long id);
	
	public E update(E model);
	
	public void delete(Long id);

	public Page<E> findPaged(Pageable pageable);
}
