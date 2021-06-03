package org.homi.plugin.specification.types;

import java.util.List;
import java.util.function.Predicate;

import org.homi.plugin.specification.exceptions.InvalidArgumentException;

public class TypeDef<T> {

	private Class<T> type;
	private List<Predicate<T>> constraints;

	@SafeVarargs
	public TypeDef(Class<T> type, Predicate<T>...constraints) {
		this.type = type;
		this.constraints = List.of(constraints);
	}
	
	public T process(Object obj, ClassLoader classLoader) throws InvalidArgumentException {
		try {
			T value = this.type.cast(obj);
			this.validateConstraints(value);
			return value;
		} catch(ClassCastException e) {
			throw new InvalidArgumentException("Argument type does not match specification [ expected "+ type.getName() +" but received " +obj.getClass().getName()+"]" ,e);
		}
	}
	
	public void validateConstraints(T value) throws InvalidArgumentException {
		for(Predicate<T> p : constraints) {
			if(!p.test(value)) {
				throw new InvalidArgumentException("Violated Constraint");
			}
		}
	}
	
	public Class<T> getType() {
		return type;
	}
}
