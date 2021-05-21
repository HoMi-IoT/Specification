package org.homi.plugin.specification;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Predicate;

public class SerializableTypeDef<T extends Serializable> extends TypeDef<T> {

	public SerializableTypeDef(Class<T> c, Predicate<T>...constraints) {
		super(c, constraints);
	}
	
	@Override
	public T process(Object obj) {
		if(!(obj instanceof Serializable)){
			throw new RuntimeException();
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
			return this.c.cast(temp);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RuntimeException();
		}
	}

}
