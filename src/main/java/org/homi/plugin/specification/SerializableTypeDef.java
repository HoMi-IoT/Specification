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

	public SerializableTypeDef(Class<T> type, Predicate<T>...constraints) {
		super(type, constraints);
	}
	
	@Override
	public T process(Object obj) throws InvalidArgumentException {
		if(!(obj instanceof Serializable)){
			throw new InvalidArgumentException("Expected argument of type ["+Serializable.class.getName()+" but received type"+ Object.class.getName() +"]");
		}

		try {
			ByteArrayOutputStream baos = new  ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(obj);
			byte[] serial = baos.toByteArray();
			oos.close();
			baos.close();

			ByteArrayInputStream bais = new  ByteArrayInputStream(serial);
			ObjectInputStream ois;
				ois = new ObjectInputStream(bais);
			
			Object temp = ois.readObject();
			ois.close();
			bais.close();
			return this.getType().cast(temp);
		} catch (IOException | ClassNotFoundException e) {
			throw new InvalidArgumentException("Error serializing  type ["+Serializable.class.getName()+" to "+ Object.class.getName() +"]", e);
		}
	}

}
