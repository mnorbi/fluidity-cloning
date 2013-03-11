package com.innometa.fluidity.cloning.fieldsetters;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum listing the very basic, funamental field
 * setters.
 * 
 * @author norbert.madarasz
 *
 */
public enum BasicFieldSetters {
	DEFAULT(new Default()),

	SKIP(new Skip()),
	
	NULL(new Null()),
	
	COPY(new Copy());
	
	
	private static Map<Class<? extends FieldSetter>, FieldSetter> INSTANCE_FOR;
	
	private final FieldSetter fieldSetter;
	
	private BasicFieldSetters(FieldSetter fieldSetter) {
		this.fieldSetter = fieldSetter;
		cache(fieldSetter);
	}

	private void cache(FieldSetter fieldSetter) {
		getCache().put(fieldSetter.getClass(), fieldSetter);
	}

	private static Map<Class<? extends FieldSetter>, FieldSetter> getCache() {
		if (INSTANCE_FOR == null){
			INSTANCE_FOR = new HashMap<Class<? extends FieldSetter>, FieldSetter>();
		}
		return INSTANCE_FOR;
	}

	public FieldSetter getFieldSetter() {
		return fieldSetter;
	}

	public static FieldSetter instanceFor(Class<? extends FieldSetter> clazz) {
		FieldSetter ret = getCache().get(clazz);
		return ret;
	}
	
}
