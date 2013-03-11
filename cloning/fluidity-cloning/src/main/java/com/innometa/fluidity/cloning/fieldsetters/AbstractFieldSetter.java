package com.innometa.fluidity.cloning.fieldsetters;
/**
 * Abstract field setter parent class as a basis
 * for the different field setter implementations.
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
public abstract class AbstractFieldSetter implements FieldSetter {

	public boolean shouldClone() {
		return false;
	}

	
}
