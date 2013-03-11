package com.rits.cloning;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author kostantinos.kougios
 *
 * 21 May 2009
 */
public class FastClonerHashMap implements IFastCloner
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object clone(final Object t, final Cloner cloner, final Map<Object, Object> clones, Set<Class<?>> activationGroups) throws IllegalAccessException
	{
		final HashMap<Object, Object> m = (HashMap) t;
		final HashMap result = new HashMap();
		for (final Map.Entry e : m.entrySet())
		{
			final Object key = cloner.cloneInternal(e.getKey(), clones, activationGroups);
			final Object value = cloner.cloneInternal(e.getValue(), clones, activationGroups);

			result.put(key, value);
		}
		return result;
	}
}
