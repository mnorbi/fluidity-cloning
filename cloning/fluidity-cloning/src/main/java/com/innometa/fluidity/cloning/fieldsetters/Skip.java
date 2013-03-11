package com.innometa.fluidity.cloning.fieldsetters;

import java.lang.reflect.Field;

/**
 * The {@link Skip} setter will leave
 * the field in the clone instance as is,
 * more specifically, as the object
 * instantiator initiates it.
 * <p/>By default {@link ObjenesisStd} is used as the
 * default instantiation strategy, which omits
 * the default constructor.
 * 
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
public class Skip extends AbstractFieldSetter {

	public void setField(Field field, Object newInstance,
			Object oldInstance, Object fieldDefaultValue) {
		//nop
	}

}
