package org.homi.plugin.specification;

import java.util.ArrayList;
import java.util.List;

public final class SpecificationHelper {
	public static List<TypeDef<?>> processTypes(Object[] parameterTypes) throws Exception {
		List<TypeDef<?>> types = new ArrayList<>();
		for(Object parameterType: parameterTypes) {
			types.add(processType(parameterType));
		}
		return types;
	}
	
	public static TypeDef<?> processType(Object parameterType) throws Exception {
		if(parameterType instanceof TypeDef)
			return (TypeDef<?>) parameterType;
		if(parameterType instanceof Class<?>)
			return new TypeDef<>((Class<?>)parameterType);
		
		throw new Exception(); 
	}
}
