package com.rits.cloning;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @author kostantinos.kougios
 *
 * 21 May 2009
 */
public class FastClonerLinkedList implements IFastCloner
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object clone(final Object t, final Cloner cloner, final Map<Object, Object> clones, Set<Class<?>> activationGroups) throws IllegalAccessException
	{
		final LinkedList al = (LinkedList) t;
		final LinkedList l = new LinkedList();
		for (final Object o : al)
		{
			final Object cloneInternal = cloner.cloneInternal(o, clones, activationGroups);
			l.add(cloneInternal);
		}
		return l;
	}
}
