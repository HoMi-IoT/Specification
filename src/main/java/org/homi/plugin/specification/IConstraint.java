package org.homi.plugin.specification;

import java.util.function.Predicate;

public interface IConstraint {

	public static <T extends Object> Predicate<T> notNull() {
		return new Predicate<T>() {
			@Override
			public boolean test(T t) {
				return t != null;
			}};
	}
	
	public static  Predicate<String> longerThan(int minLength) {
		return new Predicate<String>() {
			@Override
			public boolean test(String t) {
				return t.length() > minLength;
			}};
	}
	
}
