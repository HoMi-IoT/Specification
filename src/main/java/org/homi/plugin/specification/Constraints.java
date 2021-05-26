package org.homi.plugin.specification;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Constraints {
	
	public static <T extends Object> Predicate<T> not(Predicate<T> p) {
		return (t) -> { return !p.test(t);};
	}

	public static <T extends Object> Predicate<T> and(Predicate<T>... constraints) {
		return (t) -> { return Arrays.asList(constraints).stream().allMatch((p)->{return p.test(t);});};
	}

	public static <T extends Object> Predicate<T> or(Predicate<T>... constraints) {
		return (t) -> { return Arrays.asList(constraints).stream().anyMatch((p)->{return p.test(t);});};
	}
	
	public static <T extends Object> Predicate<T> notNull() {
		return (t) -> {return t != null;};
	}

	public static <T extends Object> Predicate<T> isEqualTo(T value) {
		return (t) -> {return t.equals(value); };
	}

	public static <T extends Object> Predicate<T> isNotEqualTo(T value) {
		return (t)-> { return !t.equals(value);	};
	}

	public static  Predicate<String> maxLength(int minLength) {
		return (t) ->{return t.length() <= minLength; };
	}
	
	public static  Predicate<String> minLength(int minLength) {
		return (t)->{return t.length() >= minLength; };
	}

	public static  Predicate<String> startsWith(String subStr) {
		return (t) ->{return t.startsWith(subStr); };
	}
	
	public static  Predicate<String> endsWith(String subStr) {
		return (t) ->{return t.endsWith(subStr); };
	}

	public static  Predicate<String> contains(String subStr) {
		return (t) ->{return t.contains(subStr); };
	}

	public static  Predicate<String> contains(CharSequence subStr) {
		return (t) ->{return t.contains(subStr); };
	}

	public static <T> Predicate<List<T>> contains(T element) {
		return (t) ->{return t.stream().anyMatch((e)->{return e.equals(element);}); };
	}

	public static <T> Predicate<T[]> arrayContains(T element) {
		return (t) ->{return Arrays.stream(t).anyMatch((e)->{return e.equals(element);}); };
	}

	public static <T> Predicate<T> isOneOf(T[] options) {
		return (t) ->{return Arrays.stream(options).anyMatch((e)->{return e.equals(t);}); };
	}
	
	public static <T> Predicate<T> isOneOf(List<T> options) {
		return (t) ->{return options.stream().anyMatch((e)->{return e.equals(t);}); };
	}

	public static <C, T extends Comparable<C>> Predicate<T> greaterThan(C value) {
		return (t) ->{return t.compareTo(value)>0;};
	}
	
	public static <C, T extends Comparable<C>> Predicate<T> lessThan(C value) {
		return (t) ->{return t.compareTo(value)<0;};
	}
	
}
