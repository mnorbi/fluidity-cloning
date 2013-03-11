package com.innometa.fluidity.cloning.hibernate.cloning;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.hibernate.collection.internal.PersistentMap;

import com.rits.cloning.Cloner;
import com.rits.cloning.IFastCloner;

public class FastClonerPersistentMap implements IFastCloner {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object clone(Object t, Cloner cloner, Map<Object, Object> clones, Set<Class<?>> activationGroups)
			throws IllegalAccessException {
		final PersistentMap m = (PersistentMap) t;
		Map ret = m;
		if (m.wasInitialized()){
			final HashMap hashMap = new HashMap();
			ret = hashMap;
			Set entrySet = m.entrySet();
			Iterator iterator = entrySet.iterator();
			while(iterator.hasNext()){
				Map.Entry e = (Map.Entry)iterator.next();
				final Object key = cloner.cloneInternal(e.getKey(), clones, activationGroups);
				final Object value = cloner.cloneInternal(e.getValue(), clones, activationGroups);
	
				hashMap.put(key, value);
			}
		}
		return ret;
	}

}
