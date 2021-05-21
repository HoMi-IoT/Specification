package org.homi.plugin.specification;

import java.util.ArrayList;
import java.util.List;

public final class SpecificationHelper {
//	public static Type<?>[] processTypes(Object[] parameterTypes) throws Exception {
//		List<Type<?>> types = new ArrayList<>();
//		for(Object parameterType: parameterTypes) {
//			types.add(processTypes(parameterType));
//		}
//		return (Type<?>[]) types.toArray();
//	}
	
	public static Type<?> processType(Object parameterType) throws Exception {
		if(parameterType instanceof Type)
			return (Type<?>) parameterType;

		if(parameterType instanceof Class<?>)
			return new Type<>((Class<?>)parameterType);
		throw new Exception(); 
	}
}
