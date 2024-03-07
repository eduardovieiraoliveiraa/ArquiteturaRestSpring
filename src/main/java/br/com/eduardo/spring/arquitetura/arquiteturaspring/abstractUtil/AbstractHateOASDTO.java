package br.com.eduardo.spring.arquitetura.arquiteturaspring.abstractUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractHateOASDTO extends AbstractDTO{

	@JsonIgnore
	public abstract Class<?> getController();
}
