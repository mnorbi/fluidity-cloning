package com.innometa.fluidity.cloning.annotations;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * Use on {@link PostClone} callback parameters,
 * to inject service classes.
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
@Target(PARAMETER)
@Retention(RUNTIME)
@Documented
public @interface CloneInject {
	String value() default "";
}
