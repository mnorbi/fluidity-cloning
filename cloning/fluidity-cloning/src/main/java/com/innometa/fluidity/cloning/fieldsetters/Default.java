package com.innometa.fluidity.cloning.fieldsetters;

import java.lang.reflect.Field;

/**
 * Default field setter, this
 * usually sets a new instance
 * in the clone.
 * 
 * @author norbert.madarasz
 *
 */
public class Default implements FieldSetter{

	public void setField(Field field, Object newInstance,
			Object oldInstance, Object fieldDefaultValue) throws IllegalArgumentException, IllegalAccessException {
		field.set(newInstance, fieldDefaultValue);
	}

	public boolean shouldClone() {
		return true;
	}

}
