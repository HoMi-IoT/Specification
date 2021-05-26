package org.homi.plugin.specification;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Predicate;

import org.homi.plugin.specification.exceptions.InvalidArgumentException;

public class SerializableTypeDef<T extends Serializable> extends TypeDef<T> {

	public SerializableTypeDef(Class<T> type, Predicate<T>... constraints) {
		super(type, constraints);
	}

	@Override
	public T process(Object obj) throws InvalidArgumentException {
		if (!(obj instanceof Serializable)) {
			throw new InvalidArgumentException("Expected argument of type [" + Serializable.class.getName()
					+ " but received type" + Object.class.getName() + "]");
		}
		return srializaThenDecerialize(obj);
	}

	private T srializaThenDecerialize(Object obj) throws InvalidArgumentException {
		try {
			byte[] serial = serialize(obj);
			Object temp = decerialize(serial);
			return this.getType().cast(temp);
		} catch (IOException | ClassNotFoundException e) {
			throw new InvalidArgumentException(
					"Error serializing  type [" + Serializable.class.getName() + " to " + Object.class.getName() + "]", e);
		}
	}

	private byte[] serialize(Object obj) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(obj);
			return baos.toByteArray();
		}
	}
	
	private Object decerialize(byte[] serial) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serial);
				ObjectInputStream ois = new ObjectInputStream(bais)) {
			Object temp = ois.readObject();
			return temp;
		}
	}
	
}
