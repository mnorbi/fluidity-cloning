package com.innometa.fluidity.cloning.hibernate.cloning;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.collection.internal.PersistentSortedSet;

import com.rits.cloning.Cloner;
import com.rits.cloning.IFastCloner;

public class FastClonerPersistentSortedSet implements IFastCloner {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object clone(Object t, Cloner cloner, Map<Object, Object> clones, Set<Class<?>> activationGroups)
			throws IllegalAccessException {
		final PersistentSortedSet ss = (PersistentSortedSet) t;
		Set ret = ss;
		if (ss.wasInitialized()){
			final LinkedHashSet l = new LinkedHashSet();
			ret = l;
			for (final Object o : ss) {
				final Object cloneInternal = cloner.cloneInternal(o, clones, activationGroups);
				l.add(cloneInternal);
			}
		}
		return ret;
	}

}
