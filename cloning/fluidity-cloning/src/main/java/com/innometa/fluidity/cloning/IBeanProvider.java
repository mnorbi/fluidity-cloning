package com.innometa.fluidity.cloning;

import com.innometa.fluidity.cloning.annotations.CloneInject;
import com.innometa.fluidity.cloning.annotations.PostClone;

/**
 * Bean lookup provider for {@link PostClone} method annotation
 * combined with {@link CloneInject} argument annotation.
 * 
 * You can easily implement one based on spring, guice,
 * other dependency injection framework or based on
 * some other custom solution.
 * 
 * @author norbert.madarasz
 *
 */
public interface IBeanProvider {
	/**
	 * Look up a bean, usually service
	 * based on bean type information
	 * @param beanType The type of the service to look up.
	 * @return The service instance found, usually a singleton.
	 */
	public Object getBean(Class<?> beanType);
	
	/**
	 * Look up a bean, usually service
	 * based on bean type and name qualifier information.
	 * 
	 * @param beanName A name qualifier to filter the look up for. 
	 * @param beanType The type of the service to look up.
	 * @return The service instance found, usually a singleton.
	 */
	public Object getBean(String beanName, Class<?> beanType);
}
