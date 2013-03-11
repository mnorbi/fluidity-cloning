package com.innometa.fluidity.cloning;


import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit test presenting the
 * features of the library.
 * 
 * @author norbert.madarasz
 *
 */
public class ClonerTest {
	@Test
	public void test(){
		Cloner cloner = 
			new Cloner(
				new String[]{CloneTarget.class.getPackage().getName()},
				new CustomBeanProvider());		
		
		CloneTarget toClone = new CloneTarget();
		CloneTarget cloned = cloner.deepClone(toClone);
		Assert.assertEquals(toClone.id1, cloned.id1);
		Assert.assertEquals(toClone.id2, cloned.id2);
		Assert.assertNull(cloned.id3);
		Assert.assertNotSame(toClone.id3, cloned.id3);
		Assert.assertNull(cloned.id4);
		Assert.assertEquals(toClone.id5, cloned.id5);
		Assert.assertEquals(toClone.id6, cloned.id6);
		Assert.assertEquals(toClone.version+1, cloned.version);
		Assert.assertEquals(toClone, cloned.original);
		Assert.assertNull(cloned.original1);
		Assert.assertNull(cloned.original23);
		Assert.assertNull(cloned.service23);
		Assert.assertNull(cloned.service4);
		Assert.assertNotNull(cloned.service);
		
		CloneTarget cloned1 = cloner.deepClone(toClone, CustomActivationGroup1.class);
		Assert.assertNotNull(cloned1.id5);
		Assert.assertNotSame(toClone.id5, cloned1.id5);
		Assert.assertEquals(toClone.id6, cloned1.id6);
		Assert.assertNull(cloned1.original);
		Assert.assertEquals(toClone, cloned1.original1);
		Assert.assertNull(cloned1.original23);
		Assert.assertNull(cloned1.service4);
		Assert.assertNull(cloned1.service);
		Assert.assertEquals(toClone.version+1, cloned1.version);
		
		CloneTarget cloned23 = cloner.deepClone(toClone, CustomActivationGroup2.class, CustomActivationGroup3.class);
		Assert.assertNotNull(cloned23.id6);
		Assert.assertNotSame(toClone.id6, cloned23.id6);
		Assert.assertNull(cloned23.original);
		Assert.assertNull(cloned23.original1);
		Assert.assertEquals(toClone, cloned23.original23);
		Assert.assertNotNull(cloned23.service23);
		Assert.assertNull(cloned23.service4);
		Assert.assertNull(cloned23.service);
		Assert.assertNull(cloned23.activationGroupOrderTest);
		cloned23 = cloner.deepClone(toClone, CustomActivationGroup3.class, CustomActivationGroup2.class);
		Assert.assertEquals(toClone.activationGroupOrderTest, cloned23.activationGroupOrderTest);
	}
}
