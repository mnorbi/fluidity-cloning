package com.innometa.fluidity.cloning;

import com.innometa.fluidity.cloning.IBeanProvider;

import junit.framework.Assert;

/**
 * A dummy bean provider implementation.
 * <p/>Use a sofisticated spring, guice or other DI
 * framework based implementation.
 * 
 * @author norbert.madarasz
 *
 */
public class CustomBeanProvider implements IBeanProvider {
	public Object getBean(String beanName, Class<?> beanType) {
		Assert.assertEquals(beanType, CloneInjectedService.class);
		return new CloneInjectedService();
	}

	public Object getBean(Class<?> beanType) {
		Assert.assertEquals(beanType, CloneInjectedService.class);
		return new CloneInjectedService();
	}
}