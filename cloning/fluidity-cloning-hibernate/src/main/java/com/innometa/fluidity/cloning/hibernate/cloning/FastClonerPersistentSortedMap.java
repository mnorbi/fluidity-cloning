package com.innometa.fluidity.cloning.hibernate.cloning;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.hibernate.collection.internal.PersistentMap;

import com.rits.cloning.Cloner;
import com.rits.cloning.IFastCloner;

public class FastClonerPersistentSortedMap implements IFastCloner {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object clone(Object t, Cloner cloner, Map<Object, Object> clones, Set<Class<?>> activationGroups)
			throws IllegalAccessException {
		final PersistentMap m = (PersistentMap) t;
		Map ret = m;
		if (m.wasInitialized()){
			final TreeMap treeMap = new TreeMap();
			ret = treeMap;
			Set entrySet = m.entrySet();
			Iterator iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Map.Entry e = (Map.Entry) iterator.next();
				final Object key = cloner.cloneInternal(e.getKey(), clones, activationGroups);
				final Object value = cloner.cloneInternal(e.getValue(), clones, activationGroups);
	
				treeMap.put(key, value);
			}
		}
		
		return ret;
	}

}
