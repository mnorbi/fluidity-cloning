package com.innometa.fluidity.cloning.hibernate.cloning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.collection.internal.PersistentList;

import com.rits.cloning.Cloner;
import com.rits.cloning.IFastCloner;

public class FastClonerPersistentList implements IFastCloner {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object clone(Object t, Cloner cloner, Map<Object, Object> clones, Set<Class<?>> activationGroups)
			throws IllegalAccessException {
		final PersistentList pl = (PersistentList) t;
		List ret = pl;
		if (pl.wasInitialized()){
			final ArrayList l = new ArrayList();
			ret = l;
			for (final Object o : pl) {
				final Object cloneInternal = cloner.cloneInternal(o, clones, activationGroups);
				l.add(cloneInternal);
			}
		}
		
		return ret;
	}

}
