package com.rits.cloning;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author kostantinos.kougios
 *
 * 21 May 2009
 */
public class FastClonerHashSet implements IFastCloner
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object clone(final Object t, final Cloner cloner, final Map<Object, Object> clones, Set<Class<?>> activationGroups) throws IllegalAccessException
	{
		final HashSet al = (HashSet) t;
		final HashSet l = new HashSet();
		for (final Object o : al)
		{
			final Object cloneInternal = cloner.cloneInternal(o, clones, activationGroups);
			l.add(cloneInternal);
		}
		return l;
	}
}
