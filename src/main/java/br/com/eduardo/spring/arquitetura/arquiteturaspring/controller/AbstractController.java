package br.com.eduardo.spring.arquitetura.arquiteturaspring.controller;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.abstractUtil.AbstractDTO;
import br.com.eduardo.spring.arquitetura.arquiteturaspring.abstractUtil.AbstractModel;
import br.com.eduardo.spring.arquitetura.arquiteturaspring.component.MapperConvertComponent;
import br.com.eduardo.spring.arquitetura.arquiteturaspring.service.ICrudService;
import jakarta.validation.Valid;


public abstract class AbstractController<E extends AbstractModel, DTO extends AbstractDTO> extends MapperConvertComponent<E,DTO>  {

    public abstract ICrudService<E> getService();
	
	@GetMapping
	public ResponseEntity<List<DTO>> findAll() {
		List<DTO> listaDTO = convertToDTO(getService().findAll());
		
		generateLinksHateoas(listaDTO);

		return ResponseEntity.status(HttpStatus.OK).body(listaDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DTO> findById(@PathVariable Long id) {
		DTO dto = convertToDTO(getService().findById(id));
		
		generateLinksHateoasJustCollection(dto);

		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping
	public ResponseEntity<DTO> create(@Valid @RequestBody DTO dto) {	
		E model = convertToEntity(dto);
		getService().save(model);
		dto = convertToDTO(model);
		
		generateLinksHateoas(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping
	public ResponseEntity<DTO> update(@Valid @RequestBody DTO dto) {	
		E model = convertToEntity(dto);
		getService().save(model);
		
		generateLinksHateoas(dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		getService().delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/paged")
	public ResponseEntity<List<DTO>> findPaged(Pageable pageable){
		 List<E> models =  getService().findPaged(pageable).getContent();
		 List<DTO> listaDTO = convertToDTO(models);
		 
		 generateLinksHateoas(listaDTO);

		 return ResponseEntity.status(HttpStatus.OK).body(listaDTO);
	}
}
