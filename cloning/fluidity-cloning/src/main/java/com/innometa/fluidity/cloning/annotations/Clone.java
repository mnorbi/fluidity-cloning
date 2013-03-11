package com.innometa.fluidity.cloning.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.innometa.fluidity.cloning.fieldsetters.Default;
import com.innometa.fluidity.cloning.fieldsetters.FieldSetter;


/**
 * A customized version of the popular {@link com.rits.cloning.Cloner} deep
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
@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface Clone {
	Class<? extends FieldSetter> value() default Default.class;
	Class<?>[] groups() default {};
	
	/**
	 * You can specify different {@link Clone} annotations
	 * for the same field using this annotation.
	 * 
	 * <p/>For detailed examples take a look at the
	 * {@link CloneTarget} example class and the
	 * {@link ClonerTest} unit test.
	 * 
	 * @author norbert.madarasz
	 *
	 */
	@Target(FIELD)
	@Retention(RUNTIME)
	@Documented
	@interface List{
		Clone[] value();
	}
}
