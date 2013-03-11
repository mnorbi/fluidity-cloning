package com.innometa.fluidity.cloning.fieldsetters;


/**
 * The field setter explicitly sets
 * the target instance field to null.
 * 
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
public class Null extends ValueFieldSetter<Object> {

	@Override
	protected Object getValue() {
		return null;
	}

}
