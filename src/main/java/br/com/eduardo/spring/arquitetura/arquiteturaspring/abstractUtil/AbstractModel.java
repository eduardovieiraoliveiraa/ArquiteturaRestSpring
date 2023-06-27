package br.com.eduardo.spring.arquitetura.arquiteturaspring.abstractUtil;

import java.util.Objects;

public abstract class AbstractModel {
    
    @Override
	public boolean equals(Object other) {
		if (other instanceof AbstractModel) {
			AbstractModel otherModel = (AbstractModel) other;
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
