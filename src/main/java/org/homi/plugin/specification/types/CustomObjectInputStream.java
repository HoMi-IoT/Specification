package org.homi.plugin.specification.types;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class CustomObjectInputStream extends ObjectInputStream {
	
	private ClassLoader cl;
	public CustomObjectInputStream(InputStream in, ClassLoader cl) throws IOException {
		super(in);
		this.cl = cl;
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        String name = desc.getName();
        try {
            return Class.forName(name, false, cl);
        } catch (Exception ex) {
            return super.resolveClass(desc);
        }
	}

}
