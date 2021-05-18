package org.homi.plugin.specification;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ParameterType<T>{
	
		private Class<T> c;
		private boolean isSerializable;

		public ParameterType(Class<T> c) {
			this.c = c;
			this.isSerializable = false;
		}
		

		public ParameterType(Class<T> c, boolean isSerializable) {
			this.c = c;
			this.isSerializable = isSerializable;
		}
		public T cast(Object obj) throws IOException, ClassNotFoundException {
			if(this.isSerializable && obj instanceof Serializable)
				return SerializeThenDeSerialize((Serializable) obj);
			return this.c.cast(obj);
		}
		private T SerializeThenDeSerialize(Serializable obj) throws IOException, ClassNotFoundException {
			ByteArrayOutputStream baos = new  ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byte[] serial = baos.toByteArray();
			oos.close();
			baos.close();

			ByteArrayInputStream bais = new  ByteArrayInputStream(serial);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object temp = ois.readObject();
			ois.close();
			bais.close();
			
			return this.c.cast(temp);
		}
		

		public Class<T> getTypeClass() {
			return c;
		}
		public boolean isSerializable() {
			return isSerializable;
		}
}

