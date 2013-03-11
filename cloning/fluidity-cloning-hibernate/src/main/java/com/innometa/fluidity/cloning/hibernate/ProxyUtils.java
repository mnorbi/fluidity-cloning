package com.innometa.fluidity.cloning.hibernate;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

/**
 * Utility class for hibernate proxy
 * handling. Proxy testing, unwrapping,
 * lazi init testing.
 * 
 * @author norbert.madarasz
 *
 */
public class ProxyUtils {
	public static boolean isProxy(Object object){
		boolean ret = false; 
		if (object != null){
			ret = HibernateProxy.class.isAssignableFrom(object.getClass());
		}
		
		return ret;
	}
	
	public static boolean isInitialized(Object proxy){
		boolean ret = !((HibernateProxy)proxy).getHibernateLazyInitializer().isUninitialized();
		return ret;
	}
	
	public static <T> T unwrapProxy(T object){
		T ret = unwrapProxy(object, null);
		return ret;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> T unwrapProxy(T object, T defaultUninitializedValue){
		T ret = (T)object;
		
		if (isProxy(object)){
			LazyInitializer initializer = ((HibernateProxy)object).getHibernateLazyInitializer();
			if (!initializer.isUninitialized()){
				ret = (T) initializer.getImplementation();
			} else {
				ret = defaultUninitializedValue;
			}
		}
		return ret;
	}
}
