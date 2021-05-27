package org.homi.plugin.specification;

import java.util.List;

public interface ISpecification {
	
	public default String id() {
		return this.getClass().getAnnotation(SpecificationID.class).id();
	}
	
	public String name();
	
	public List<TypeDef<?>> getParameterTypes();
	
	public TypeDef<?> getReturnType();
	
}
