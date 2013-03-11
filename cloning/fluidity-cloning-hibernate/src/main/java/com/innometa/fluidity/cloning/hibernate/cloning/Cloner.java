package com.innometa.fluidity.cloning.hibernate.cloning;

import java.util.Map;
import java.util.Set;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.internal.PersistentList;
import org.hibernate.collection.internal.PersistentMap;
import org.hibernate.collection.internal.PersistentSet;
import org.hibernate.collection.internal.PersistentSortedMap;
import org.hibernate.collection.internal.PersistentSortedSet;

import com.innometa.fluidity.cloning.IBeanProvider;
import com.innometa.fluidity.cloning.hibernate.ProxyUtils;

/**
 * This is a special cloner tuned for
 * hibernate. Handles proxies, hibernate
 * specific collections, lazy initiation.
 * 
 * @author norbert.madarasz
 *
 */
public class Cloner extends com.innometa.fluidity.cloning.Cloner {
	public Cloner(String[] enabledPackageNames, IBeanProvider beanProvider) {
		super(enabledPackageNames, beanProvider);
	}
	
	@Override
	protected void registerFastCloners() {
		super.registerFastCloners();
		registerFastCloner(PersistentList.class, new FastClonerPersistentList());
		registerFastCloner(PersistentMap.class, new FastClonerPersistentMap());
		registerFastCloner(PersistentSortedMap.class, new FastClonerPersistentSortedMap());
		registerFastCloner(PersistentSet.class, new FastClonerPersistentSet());
		registerFastCloner(PersistentSortedSet.class, new FastClonerPersistentSortedSet());
		registerFastCloner(PersistentBag.class, new FastClonePersistentBag());
	}
	
	public <T> T deepClone(T o, java.lang.Class<?> ... activationGroups) {
		return super.deepClone(o, activationGroups);
	};
	
	@Override
	protected void initialize() {
		super.initialize();
		enablePackage("org.hibernate.collection");
	}
	
	public <T> T cloneInternal(T o, java.util.Map<Object,Object> clones, Set<Class<?>> activationGroups) throws IllegalAccessException {
		T ret = null;
		
		if (ProxyUtils.isProxy(o)){
			T unproxied = ProxyUtils.unwrapProxy(o);
			if (unproxied != null){
				ret = super.cloneInternal(unproxied, clones, activationGroups);
			} else {
				ret = unproxied;
			}
		} else {
			ret = super.cloneInternal(o, clones, activationGroups);
		}
		
		return ret;
	};
	
	@Override
	protected Object fastClone(Object o, Map<Object, Object> clones, Set<Class<?>> activationGroups)
			throws IllegalAccessException {
		Object ret;
		if (ProxyUtils.isProxy(o)){
			Object unproxied = ProxyUtils.unwrapProxy(o);
			if (unproxied != null){
				ret = super.fastClone(unproxied, clones, activationGroups);
			} else {
				ret = unproxied;
			}
		} else {
			ret = super.fastClone(o, clones, activationGroups);
		}
		return ret;
	}

}
