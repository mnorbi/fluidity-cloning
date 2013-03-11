package com.innometa.fluidity.cloning.fieldsetters;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Random UUID generating field setter.
 * 
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
public class RandomUUID extends AbstractFieldSetter {
	public static final String ID = "com.innometa.fluidity.cloning.RandomUUIDStringFieldSetter";
	
	public void setField(Field field, Object newInstance,
			Object oldInstance, Object fieldDefaultValue) throws IllegalArgumentException, IllegalAccessException {
		Object value = UUID.randomUUID();
		if (field.getType().equals(String.class)){
			value = value.toString();
		}
		
		field.set(newInstance, value);
	}

}
