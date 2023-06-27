package br.com.eduardo.spring.arquitetura.arquiteturaspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class MapperConvertService<E,DTO> {

	public abstract Class<E> getModelClass();
	public abstract Class<DTO> getDTOClass();

	@Autowired
    private ModelMapper modelMapper;
        
    public E convertToEntity(Optional<DTO> dto) {
		return convertToEntity(dto.orElse(null));
	}

	public DTO convertToDTO(Optional<E> entity) {
		return convertToDTO(entity.orElse(null));
	}
	
	public E convertToEntity(DTO dto) {
		return modelMapper.map(dto, getModelClass());
	}

	public DTO convertToDTO(E entity) {
		return modelMapper.map(entity, getDTOClass());
	}

    public List<E> convertToEntity(List<DTO> dtoList) {
		List<E> arrayList = new ArrayList<>();
		dtoList.forEach(dto -> arrayList.add(convertToEntity(dto)));
		return arrayList;
	}
	
	public List<DTO> convertToDTO(List<E> entityList) {
		List<DTO> arrayList = new ArrayList<>();
		entityList.forEach(entity -> arrayList.add(convertToDTO(entity)));
		return arrayList;
	}
    
}
