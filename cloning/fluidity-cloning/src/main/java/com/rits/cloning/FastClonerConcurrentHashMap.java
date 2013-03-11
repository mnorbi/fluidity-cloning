package com.rits.cloning;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kostantinos.kougios
 *
 * 18 Oct 2011
 */
public class FastClonerConcurrentHashMap implements IFastCloner
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object clone(final Object t, final Cloner cloner, final Map<Object, Object> clones, Set<Class<?>> activationGroups) throws IllegalAccessException
	{
		final ConcurrentHashMap<Object, Object> m = (ConcurrentHashMap) t;
		final ConcurrentHashMap result = new ConcurrentHashMap();
		for (final Map.Entry e : m.entrySet())
		{
			final Object key = cloner.cloneInternal(e.getKey(), clones, activationGroups);
			final Object value = cloner.cloneInternal(e.getValue(), clones, activationGroups);

			result.put(key, value);
		}
		return result;
	}
}
