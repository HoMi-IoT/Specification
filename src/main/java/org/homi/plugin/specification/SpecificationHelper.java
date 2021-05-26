package org.homi.plugin.specification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class SpecificationHelper {
	
	@SafeVarargs
	public static <T> TypeDef<T> defineType(Class<T> type, Predicate<T>...constraints){
		return new TypeDef<T>(type, constraints);
	}
	
	@SafeVarargs
	public static <T extends Serializable> SerializableTypeDef<T> defineSerializableType(Class<T> type, Predicate<T>...constraints){
		return new SerializableTypeDef<T>(type, constraints);
	}

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
