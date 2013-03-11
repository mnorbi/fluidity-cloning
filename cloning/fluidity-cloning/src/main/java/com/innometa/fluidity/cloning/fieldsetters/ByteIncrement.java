package com.innometa.fluidity.cloning.fieldsetters;

import java.lang.reflect.Field;

/**
 * Byte incrementing field setter.
 * 
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
public class ByteIncrement implements FieldSetter {

	public void setField(Field field, Object newInstance, Object oldInstance,
			Object fieldDefaultValue) throws IllegalArgumentException,
			IllegalAccessException {
		field.set(newInstance, ((Byte)fieldDefaultValue)+1);
	}

	public boolean shouldClone() {
		return true;
	}

}
