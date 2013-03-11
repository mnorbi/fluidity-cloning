package com.innometa.fluidity.cloning.fieldsetters;

import java.lang.reflect.Field;

import com.innometa.fluidity.cloning.ICloner;

/**
 * Field setter contract used by
 * the {@link ICloner} to populate
 * fields during the cloning process.
 * 
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
public interface FieldSetter {
	public void setField(Field field, Object newInstance, Object oldInstance, Object fieldDefaultValue) throws IllegalArgumentException, IllegalAccessException;
	public boolean shouldClone();
}
