package org.homi.plugin.specification;

import java.util.List;
import java.util.function.Predicate;

public class TypeDef<T> {

	protected Class<T> c;
	private List<Predicate<T>> constraints;

	public TypeDef(Class<T> c, Predicate<T>...constraints) {
		this.c = c;
		this.constraints = List.of(constraints);
	}
	
	public T process(Object obj) {
		T value = this.c.cast(obj);
		this.validateConstraints(value);
		return value;
	}
	
	public void validateConstraints(T value) {
		for(Predicate<T> p : constraints) {
			if(!p.test(value)) {
				throw new Error();
			}
		}
	}
	
	public Class<T> getTypeClass() {
		return c;
	}
}
