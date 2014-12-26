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
	private final Cloner cloner = 
			new Cloner(
					new String[]{CloneTarget.class.getPackage().getName()},
					new CustomBeanProvider());		

	@Test
	public void testDefaultCloning() {
		CloneTarget toClone = new CloneTarget();
		CloneTarget cloned = cloner.deepClone(toClone);
		Assert.assertEquals(toClone, cloned.originalForDefaultActivationGroup);
		Assert.assertNull(cloned.originalForActivationGroup1);
		Assert.assertNull(cloned.originalForActivationGroups2And3);
		Assert.assertEquals(toClone.id1, cloned.id1);
		Assert.assertEquals(toClone.id2, cloned.id2);
		Assert.assertNull(cloned.id3);
		Assert.assertNotSame(toClone.id3, cloned.id3);
		Assert.assertNull(cloned.id4);
		Assert.assertEquals(toClone.id5, cloned.id5);
		Assert.assertEquals(toClone.id6, cloned.id6);
		Assert.assertTrue(cloned.wasServiceCallForDefaultActivationGroup);
		Assert.assertFalse(cloned.wasServiceCallForActivationGroups2And3);
		Assert.assertFalse(cloned.wasServiceCallForActivationGroup4);
		Assert.assertEquals(toClone.version+1, cloned.version);
	}
	
	@Test
	public void testSingleActivationGroupBasedCloning() {
		CloneTarget toClone = new CloneTarget();
		CloneTarget cloned = cloner.deepClone(toClone, CustomActivationGroup1.class);
		Assert.assertNull(cloned.originalForDefaultActivationGroup);
		Assert.assertEquals(toClone, cloned.originalForActivationGroup1);
		Assert.assertNull(cloned.originalForActivationGroups2And3);
		Assert.assertNotNull(cloned.id5);
		Assert.assertNotSame(toClone.id5, cloned.id5);
		Assert.assertEquals(toClone.id6, cloned.id6);
		Assert.assertFalse(cloned.wasServiceCallForActivationGroup4);
		Assert.assertFalse(cloned.wasServiceCallForDefaultActivationGroup);
		Assert.assertEquals(toClone.version+1, cloned.version);
	}

	@Test
	public void testMultiActivationGroupBasedCloning(){
		CloneTarget toClone = new CloneTarget();
		CloneTarget cloned = cloner.deepClone(toClone, CustomActivationGroup2.class, CustomActivationGroup3.class);
		Assert.assertNull(cloned.originalForDefaultActivationGroup);
		Assert.assertNull(cloned.originalForActivationGroup1);
		Assert.assertEquals(toClone, cloned.originalForActivationGroups2And3);
		Assert.assertNotNull(cloned.id6);
		Assert.assertNotSame(toClone.id6, cloned.id6);
		Assert.assertFalse(cloned.wasServiceCallForDefaultActivationGroup);
		Assert.assertTrue(cloned.wasServiceCallForActivationGroups2And3);
		Assert.assertFalse(cloned.wasServiceCallForActivationGroup4);
		Assert.assertNull(cloned.activationGroupOrderTest);
		cloned = cloner.deepClone(toClone, CustomActivationGroup3.class, CustomActivationGroup2.class);
		Assert.assertEquals(toClone.activationGroupOrderTest, cloned.activationGroupOrderTest);
	}
	
}
