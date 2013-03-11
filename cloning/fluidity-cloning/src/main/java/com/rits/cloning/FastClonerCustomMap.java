package com.rits.cloning;

import java.util.Map;
import java.util.Set;

/**
 * @author kostantinos.kougios
 *
 * 21 May 2009
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class FastClonerCustomMap<T extends Map> implements IFastCloner
{
	public Object clone(final Object t, final Cloner cloner, final Map<Object, Object> clones, Set<Class<?>> activationGroups) throws IllegalAccessException
	{
		final T m = (T) t;
		final T result = getInstance((T) t);
		final Set<Map.Entry<Object, Object>> entrySet = m.entrySet();
		for (final Map.Entry e : entrySet)
		{
			final Object key = cloner.cloneInternal(e.getKey(), clones, activationGroups);
			final Object value = cloner.cloneInternal(e.getValue(), clones, activationGroups);
			result.put(key, value);
		}
		return result;
	}

	protected abstract T getInstance(T t);
}
