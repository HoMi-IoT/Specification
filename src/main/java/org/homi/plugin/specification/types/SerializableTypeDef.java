package org.homi.plugin.specification.types;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Predicate;

import org.homi.plugin.specification.exceptions.InvalidArgumentException;

public class SerializableTypeDef<T extends Serializable> extends TypeDef<T> {

	@SafeVarargs
	public SerializableTypeDef(Class<T> type, Predicate<T>... constraints) {
		super(type, constraints);
	}

	@Override
	public T process(Object obj, ClassLoader classLoader) throws InvalidArgumentException {
		if (!(obj instanceof Serializable)) {
			throw new InvalidArgumentException("Expected argument of type [" + Serializable.class.getName()
					+ " but received type" + Object.class.getName() + "]");
		}
		if(classLoader == null)
			classLoader = this.getType().getClassLoader();
		return srializeThenDecerialize(obj, classLoader);
	}

	private T srializeThenDecerialize(Object obj, ClassLoader classLoader) throws InvalidArgumentException {
		try {
			byte[] serial = serializeToByteArray(obj);
			Object temp = decerializeFromByteArray(serial, classLoader);
			return (T) temp;
		} catch (IOException | ClassNotFoundException e) {
			throw new InvalidArgumentException(
					"Error serializing  type [" + Serializable.class.getName() + " to " + Object.class.getName() + "]", e);
		}
	}

	private byte[] serializeToByteArray(Object obj) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(obj);
			return baos.toByteArray();
		}
	}
	
	private Object decerializeFromByteArray(byte[] serial, ClassLoader classLoader) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serial);
				ObjectInputStream ois = new CustomObjectInputStream(bais, classLoader)) {
			Object temp = ois.readObject();
			return temp;
		}
	}
	
}
