package com.innometa.fluidity.cloning.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Zero or one argument method callback after cloning. The one argument callback
 * gets the original cloned peer object as input. Do not alter equals, hashcode
 * fields in the callback unless you know what you are doing!
 * 
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface PostClone {

	Class<?>[] value() default {};
}
