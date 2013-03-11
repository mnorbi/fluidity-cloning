package com.innometa.fluidity.cloning.annotations;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.innometa.fluidity.cloning.fieldsetters.BasicFieldSetters;
import com.innometa.fluidity.cloning.fieldsetters.FieldSetter;

/**
 * 
 * @author norbert.madarasz
 *
 */
public class CloneAnnotationProcessor {

	private final Map<Field, Map<Class<?>, FieldSetter>> fieldSetterCache = new HashMap<Field, Map<Class<?>,FieldSetter>>();

	protected Map<Class<?>, FieldSetter> computeActivationGroups(Field field, Clone cloneOverride) {
		Map<Class<?>, FieldSetter> map =  new HashMap<Class<?>, FieldSetter>();
		
		FieldSetter fieldSetter = getFieldSetter(cloneOverride);
		
		if (cloneOverride != null && cloneOverride.groups() != null && cloneOverride.groups().length > 0){
			for (Class<?> group : cloneOverride.groups()) {
				if (!map.containsKey(group)){
					map.put(group, fieldSetter);
				}
			}
		} else {
			map.put(DefaultActivationGroup.class, fieldSetter);
		}
		
		return map;
	}

	protected Map<Class<?>, FieldSetter> computeFieldSetters(Field field){
		Map<Class<?>, FieldSetter> map = new HashMap<Class<?>, FieldSetter>();
		Clone.List cloneOverrides = field.getAnnotation(Clone.List.class);
		if (cloneOverrides != null && cloneOverrides.value() != null){
			for (Clone cloneOverride : cloneOverrides.value()) {
				map.putAll(computeActivationGroups(field, cloneOverride));
			}
		} else {
			Clone cloneOverride = field.getAnnotation(Clone.class);
			map.putAll(computeActivationGroups(field, cloneOverride));
		}
		return map;
	}

	protected FieldSetter getCustomFieldSetter(Clone cloneOverride) {
		FieldSetter ret = BasicFieldSetters.instanceFor(cloneOverride.value());
		if (ret == null){
			ret = instantiateCustomFieldSetter(cloneOverride.value());
		}
		
		return ret;
	}

	protected FieldSetter getDefaultFieldSetter() {
		return BasicFieldSetters.DEFAULT.getFieldSetter();
	}

	protected FieldSetter getFieldSetter(Clone cloneOverride) {
		FieldSetter fieldSetter;
		if (cloneOverride != null){
			fieldSetter = getCustomFieldSetter(cloneOverride);
		} else {
			fieldSetter = getDefaultFieldSetter();
		}
		
		return fieldSetter;
	}

	public FieldSetter getFieldSetter(Field field, Set<Class<?>> activationGroups) {
		Map<Class<?>, FieldSetter> fieldSetters = getFieldSetters(field);
		FieldSetter ret = null;
		for (Class<?> activationGroup : activationGroups) {
			if (fieldSetters.containsKey(activationGroup)){
				ret = fieldSetters.get(activationGroup);
				break;
			}
		}
		if (ret == null){
			ret = fieldSetters.get(DefaultActivationGroup.class);
		}
		
		if (ret == null){
			ret = getDefaultFieldSetter();
		}
		return ret;
	}

	public Map<Class<?>, FieldSetter> getFieldSetters(Field field){
		Map<Class<?>, FieldSetter> ret;
		if (!fieldSetterCache.containsKey(field)){
			ret = computeFieldSetters(field);
			fieldSetterCache.put(field, ret);
		}
		ret = fieldSetterCache.get(field);
		
		return ret;
	}

	protected FieldSetter instantiateCustomFieldSetter(Class<? extends FieldSetter> customFieldSetterClass) {
		FieldSetter ret = null;
		try {
			ret = customFieldSetterClass.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(String.format("custom-field-setter-instantiation-error[%s]", customFieldSetterClass), e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(String.format("custom-field-setter-constructor-access-error[%s]", customFieldSetterClass), e);
		}
		return ret;
	}

}
