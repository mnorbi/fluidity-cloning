package com.innometa.fluidity.cloning.hibernate.cloning;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.collection.internal.PersistentSet;

import com.rits.cloning.Cloner;
import com.rits.cloning.IFastCloner;

public class FastClonerPersistentSet implements IFastCloner {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object clone(Object t, Cloner cloner, Map<Object, Object> clones, Set<Class<?>> activationGroups)
			throws IllegalAccessException {
		final PersistentSet ps = (PersistentSet) t;
		Set ret = ps;
		if (ps.wasInitialized()){
			final HashSet l = new HashSet();
			ret = l;
			for (final Object o : ps) {
				final Object cloneInternal = cloner.cloneInternal(o, clones, activationGroups);
				l.add(cloneInternal);
			}
		}
		return ret;
	}

}
