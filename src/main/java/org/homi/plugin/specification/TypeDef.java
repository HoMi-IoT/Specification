package org.homi.plugin.specification;

import java.util.List;
import java.util.function.Predicate;

import org.homi.plugin.specification.exceptions.InvalidArgumentException;

public class TypeDef<T> {

	protected Class<T> c;
	private List<Predicate<T>> constraints;

	public TypeDef(Class<T> c, Predicate<T>...constraints) {
		this.c = c;
		this.constraints = List.of(constraints);
	}
	
	public T process(Object obj) throws InvalidArgumentException {
		try {
			T value = this.c.cast(obj);
			this.validateConstraints(value);
			return value;
		} catch(ClassCastException e) {
			throw new InvalidArgumentException("Argument type does not match specification [ expected "+ c.getName() +"but received" +obj.getClass().getName()+"]" ,e);
		}
	}
	
	public void validateConstraints(T value) throws InvalidArgumentException {
		for(Predicate<T> p : constraints) {
			if(!p.test(value)) {
				throw new InvalidArgumentException("Violated Constraint");
			}
		}
	}
	
	public Class<T> getTypeClass() {
		return c;
	}
}
