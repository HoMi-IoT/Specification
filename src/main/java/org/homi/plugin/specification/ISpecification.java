package org.homi.plugin.specification;

import java.util.List;

public interface ISpecification {
	
	public String name();
	
	public List<TypeDef<?>> getParameterTypes();
	
	public TypeDef<?> getReturnType();
	
}
