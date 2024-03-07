package br.com.eduardo.spring.arquitetura.arquiteturaspring.component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import org.slf4j.LoggerFactory;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.abstractUtil.AbstractDTO;
import br.com.eduardo.spring.arquitetura.arquiteturaspring.annotation.ShowLinkHateoas;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Component
public abstract class HateoasComponet<DTO> {

    @SuppressWarnings("unchecked")
    protected void generateLinksHateoas(List<DTO> listaDTO){
		if(!listaDTO.isEmpty())
			listaDTO.stream().forEach(objetoDTO -> {
				((RepresentationModel<AbstractDTO>) objetoDTO).add(linkTo(getClass()).slash(((AbstractDTO) objetoDTO).getId()).withSelfRel());
				setLinkHateoasDetails(objetoDTO);
			});
	}

	@SuppressWarnings("unchecked")
	protected void setLinkHateoasDetails(DTO objetoDTO) {
		for (Field atributo : objetoDTO.getClass().getDeclaredFields()) {
			if (atributo.getType().isAnnotationPresent(ShowLinkHateoas.class)) {
				atributo.setAccessible(true);

				try {
					var objetoFilhoDTO = atributo.get(objetoDTO);
					setLinkDetails(objetoFilhoDTO);
					
				} catch (Exception e) {
					LoggerFactory.getLogger(getClass()).error(e.getMessage());
				}
			}else if(atributo.getType().equals(java.util.List.class) || atributo.getType().equals(java.util.Set.class)) {		
				atributo.setAccessible(true);
				
				try {
					var classListDTO = atributo.get(objetoDTO);
						
					for (int i = 0; i < ((List<DTO>) classListDTO).size(); i++) {
						var objetoFilhoDTO = ((List<DTO>) classListDTO).get(i);
						
						if(objetoFilhoDTO.getClass().isAnnotationPresent(ShowLinkHateoas.class))
							setLinkDetails(objetoFilhoDTO);
					}			
					
				} catch (Exception e) {
					LoggerFactory.getLogger(getClass()).error(e.getMessage());
				}
		
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void setLinkDetails(Object objetoFilhoDTO) {
		var controller = ((AbstractDTO) objetoFilhoDTO).getController();

		((RepresentationModel<AbstractDTO>) objetoFilhoDTO).add(WebMvcLinkBuilder.linkTo(controller).slash(((AbstractDTO) objetoFilhoDTO).getId()).withSelfRel());
        ((RepresentationModel<AbstractDTO>) objetoFilhoDTO).add(linkTo(controller).withRel(IanaLinkRelations.COLLECTION));
	}

    @SuppressWarnings("unchecked")
	protected void generateLinksHateoas(DTO dto){
		if(!Objects.isNull(dto)){
			((RepresentationModel<AbstractDTO>) dto).add(linkTo(getClass()).slash(((AbstractDTO) dto).getId()).withSelfRel());
			((RepresentationModel<AbstractDTO>) dto).add(linkTo(getClass()).withRel(IanaLinkRelations.COLLECTION));
			setLinkHateoasDetails(dto);
		}
	}

    @SuppressWarnings("unchecked")
	protected void generateLinksHateoasJustCollection(DTO dto){
		if(!Objects.isNull(dto)){
			((RepresentationModel<AbstractDTO>) dto).add(linkTo(getClass()).withRel(IanaLinkRelations.COLLECTION));
			setLinkHateoasDetails(dto);
		}
	}
    
}

