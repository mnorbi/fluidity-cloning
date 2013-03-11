package com.innometa.fluidity.cloning.fieldsetters;

import java.lang.reflect.Field;

/**
 * Field setter strategy for copying the original object reference instead of creating a clone.
 * 
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
public class Copy extends AbstractFieldSetter {

	public void setField(Field field, Object newInstance, Object oldInstance,
			Object fieldDefaultValue) throws IllegalArgumentException, IllegalAccessException {
		field.set(newInstance, fieldDefaultValue);
	}

}
