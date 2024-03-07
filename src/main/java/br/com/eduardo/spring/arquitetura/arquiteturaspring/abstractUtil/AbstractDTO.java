package br.com.eduardo.spring.arquitetura.arquiteturaspring.abstractUtil;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

public abstract class AbstractDTO extends RepresentationModel<AbstractDTO>{

    @Override
	public boolean equals(Object other) {

		if (other instanceof AbstractDTO) {
			AbstractDTO otherModel = (AbstractDTO) other;
			return Objects.equals(getId(), otherModel.getId());
		}
		return false;
	}

	public abstract Long getId();
	
	@Override
	public int hashCode() {
		return Objects.nonNull(getId()) ? getId().hashCode() : super.hashCode();
	}

	@Override
	public String toString() {
		return new StringBuilder().append(getClass().getName()).append("[").append(getId()).append("]").toString();
	}
    
}
