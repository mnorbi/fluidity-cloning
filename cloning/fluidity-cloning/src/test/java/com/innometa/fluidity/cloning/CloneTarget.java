package com.innometa.fluidity.cloning;

import java.util.UUID;

import org.objenesis.ObjenesisStd;

import com.innometa.fluidity.cloning.annotations.Clone;
import com.innometa.fluidity.cloning.annotations.CloneInject;
import com.innometa.fluidity.cloning.annotations.DefaultActivationGroup;
import com.innometa.fluidity.cloning.annotations.PostClone;
import com.innometa.fluidity.cloning.fieldsetters.Copy;
import com.innometa.fluidity.cloning.fieldsetters.Default;
import com.innometa.fluidity.cloning.fieldsetters.LongIncrement;
import com.innometa.fluidity.cloning.fieldsetters.Null;
import com.innometa.fluidity.cloning.fieldsetters.RandomUUID;
import com.innometa.fluidity.cloning.fieldsetters.Skip;

/**
 * Example class presenting
 * the features of this
 * cloning tool.
 * Take a look at the {@link ClonerTest}
 * for the corresponding unit test.
 * 
 * @author norbert.madarasz
 *
 */
@SuppressWarnings("unused")
public class CloneTarget {
	/**
	 * Always a new instance will be created from this field
	 * in the clone instance.
	 */
	String id1 = UUID.randomUUID().toString();
	
	/**
	 * With the {@link Clone} annotation you
	 * can customize the cloning behavior.
	 * <p/>The {@link Default} setter will create
	 * a new instance for the field.
	 */
	@Clone(Default.class)
	String id2 = UUID.randomUUID().toString();
	
	/**
	 * The {@link Skip} setter will leave
	 * the field in the clone instance as is,
	 * more specifically, as the object
	 * instantiator initiates it.
	 * <p/>By default {@link ObjenesisStd} is used as the
	 * default instantiation strategy, which omits
	 * the default constructor.
	 */
	@Clone(Skip.class)
	String id3 = UUID.randomUUID().toString();
	
	/**
	 * The {@link Null} setter will explicitly
	 * set the value of the field in the clone
	 * instance to null.
	 */
	@Clone(Null.class)
	String id4 = UUID.randomUUID().toString();
	
	/**
	 * The {@link RandomUUID} setter will generate
	 * a random UUID.
	 * <p/>The {@link CustomActivationGroup1} activation
	 * group is a marker on the {@link Clone} annotation.
	 * <br/>The annotation will be active only, if you explicitly
	 * activate the cloning process with the marker, like
	 * this: <br/>{@link ICloner#deepClone(Object, CustomActivationGroup1.class)}
	 * <p/>By default, if you specify no activation group,
	 * the {@link DefaultActivationGroup} is active.
	 */
	@Clone(value = RandomUUID.class, groups=CustomActivationGroup1.class)
	String id5 = UUID.randomUUID().toString();
	
	/**
	 * You can specify multiple activation
	 * groups for a specific setter.
	 */
	@Clone(value = RandomUUID.class, groups={CustomActivationGroup2.class, CustomActivationGroup3.class})
	String id6 = UUID.randomUUID().toString();

	/**
	 * You can specify different setters
	 * for different activation groups.
	 * <p/>The {@link Copy} setter sets the
	 * same reference as in the original. 
	 */
	@Clone.List({
		@Clone(groups=CustomActivationGroup2.class, value=Skip.class),
		@Clone(groups=CustomActivationGroup3.class, value=Copy.class)})
	Object activationGroupOrderTest = new Object();
	
	/**
	 * The {@link LongIncrement} setter
	 * sets the increment of the original,
	 * in the clone instance.
	 */
	@Clone(LongIncrement.class)
	long version = 1l;

	
	CloneTarget original;
	/**
	 * With {@link PostClone} annotation
	 * you can customize the cloning process.
	 * After deep cloning for the given instance is finished,
	 * all the post clone callbacks are invoked
	 * with the original instance as argument.
	 * 
	 * @param original source instance
	 */
	@PostClone
	private void postClone(CloneTarget original){
		this.original = original;
	}
	
	CloneInjectedService service;
	
	/**
	 * The {@link PostClone} annotation
	 * accepts {@link CloneInject} annotated.
	 * arguments for service injection. You can
	 * further customize the cloning behavior in
	 * the post clone callback.
	 * <p/> The injected service is looked up
	 * by the {@link IBeanProvider}.
	 * <p/>You have to provide a {@link IBeanProvider},
	 * when you instantiate your chosen {@link ICloner} implementation.
	 * You can easily create a spring, guice,
	 * other dependency injection framework based or custom
	 * bean provider here.
	 *  
	 * @param service The injected service, for custom behavior implementation. 
	 * The service is provided by an {@link IBeanProvider}
	 * implementation.
	 */
	@PostClone
	private void injectService(@CloneInject CloneInjectedService service){
		this.service = service;
	}
	
	CloneTarget original1;
	/**
	 * {@link PostClone} annotations could also be switched on or
	 * off based on the chosen activation group.
	 * 
	 * @param original the source instance
	 */
	@PostClone(CustomActivationGroup1.class)
	private void postClone1(CloneTarget original){
		this.original1 = original;
	}
	
	CloneInjectedService service23;
	CloneTarget original23;
	
	/**
	 * {@link PostClone} annotations also work with 
	 * multiple activation groups.
	 * <p/>You can use multiple {@link CloneInject} annotated arguments,
	 * but only one argument without the {@link CloneInject} annotation
	 * and with the same class as the clone source.
	 * 
	 * @param original the source instance 
	 * @param service The injected service, for custom behavior implementation. 
	 */
	@PostClone({CustomActivationGroup2.class, CustomActivationGroup3.class})
	private void postClone23(CloneTarget original, @CloneInject CloneInjectedService service){
		this.original23 = original;
		this.service23 = service;
	}
	
	CloneInjectedService service4;
	@PostClone(CustomActivationGroup4.class)
	private void postClone4(@CloneInject CloneInjectedService service){
		this.service4 = service; 
	}
	
}