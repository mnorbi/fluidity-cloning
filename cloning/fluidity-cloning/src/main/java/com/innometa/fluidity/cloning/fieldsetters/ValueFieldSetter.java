package com.innometa.fluidity.cloning.fieldsetters;

import java.lang.reflect.Field;

/**
 * Base class for field setters with type parameter.
 * 
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
public abstract class ValueFieldSetter<T> extends AbstractFieldSetter {

	public void setField(Field field, Object newInstance, Object oldInstance,
			Object fieldDefaultValue) throws IllegalArgumentException,
			IllegalAccessException {
		field.set(newInstance, getValue());
	}

	protected abstract T getValue();

}
