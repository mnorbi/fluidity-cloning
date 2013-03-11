package com.innometa.fluidity.cloning;

import com.innometa.fluidity.cloning.annotations.Clone;

/**
 * The implementations of the interface provide
 * a customized version of the popular {@link com.rits.cloning.Cloner} deep
 * cloning library.
 * <br/>The cloning behavior could be customized by different
 * annotations, specified in the source object's class.
 * <p/>You can find the annotations in the
 * the {@link com.innometa.fluidity.cloning.annotations} package.
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
public interface ICloner {
	/**
	 * 
	 * @param o The source object that has to be cloned
	 * @return The new deep clone.
	 */
	public <T> T deepClone(final T o);
	
	/**
	 * 
	 * @param o The source object that has to be cloned
	 * @param activationGroups Specifies which {@link Clone} annotation configurations should
	 * be active during the cloning process. For further explanations
	 * take a look at the {@link CloneTarget} and {@link ClonerTest} examples.
	 * 
	 * @return The new deep clone.
	 */
	public <T> T deepClone(final T o, Class<?> ... activationGroups);
}
