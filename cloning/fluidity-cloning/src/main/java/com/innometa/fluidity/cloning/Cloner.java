package com.innometa.fluidity.cloning;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.innometa.fluidity.cloning.annotations.AnnotationProcessor;
import com.innometa.fluidity.cloning.fieldsetters.FieldSetter;

/**
 * This is a customized version of the
 * popular {@link com.rits.cloning.Cloner} deep
 * cloning library.
 * <br/>The cloning behavior could be customized by different
 * annotations specified in the source object's class.
 * <p/>You can find the annotations in the
 * the {@link com.innometa.fluidity.cloning.annotations} package.
 * <p/>For detailed examples take a look at the
 * {@link CloneTarget} example class and the
 * {@link ClonerTest} unit test.
 * 
 * @author norbert.madarasz
 *
 */
public class Cloner extends com.rits.cloning.Cloner implements ICloner {
	private AnnotationProcessor annotationProcessor;
	private final Set<String> enabledPackageNames = new HashSet<String>();
	
	public Cloner(String[] enabledPackageNames, IBeanProvider injectableBeanProvider) {
		this(injectableBeanProvider);
		this.enabledPackageNames.addAll(Arrays.asList(enabledPackageNames));
		initialize();
	}
	
	protected Cloner(IBeanProvider injectableBeanProvider) {
		super();
		this.annotationProcessor = new AnnotationProcessor(injectableBeanProvider);
	}

	protected void enablePackage(String packageName){
		this.enabledPackageNames.add(packageName);
	}
	
	protected void initialize() {
		enablePackage("java.util");
		enablePackage("java.lang");
	}

	@Override
	public boolean shouldClone(Field field, Set<Class<?>> activationGroups) {
		boolean shouldClone = super.shouldClone(field, activationGroups);
		shouldClone = shouldClone && hasEnabledPackage(field);
		shouldClone = shouldClone && annotationProcessor.getFieldSetter(field, activationGroups).shouldClone();
		return shouldClone;
	}

	protected boolean hasEnabledPackage(Field field) {
		boolean ret = false;
		String typeName = field.getType().getCanonicalName();
		ret = field.getType().isPrimitive();
		if (!ret){
			for (String string : this.enabledPackageNames) {
				ret = typeName.startsWith(string);
				if (ret){
					break;
				}
			}
		}
		return ret;
	}

	@Override
	public void setField(Field field, Object newInstance,
			Object oldInstance, Object fieldDefaultValue, Set<Class<?>> activationGroups) throws IllegalArgumentException, IllegalAccessException {
		FieldSetter fieldSetter = annotationProcessor.getFieldSetter(field, activationGroups);
		if (fieldSetter == null){
			throw new IllegalStateException("field-setter-must-not-be-null");
		}
		fieldSetter.setField(field, newInstance, oldInstance, fieldDefaultValue);
	}
	
	@Override
	public void finishedCloning(Object clone, Object original, Set<Class<?>> activationGroups) {
		super.finishedCloning(clone, original, activationGroups);
		postCloneCallbacks(clone, original, activationGroups);
	}
	
	protected <T> void postCloneCallbacks(T clone, T original, Set<Class<?>> activationGroups){
		try {
			List<Method> postCloneCallbacks = annotationProcessor
					.getPostCloneCallbacks(clone, activationGroups);
			for (Method method : postCloneCallbacks) {
				Object[] parameters = annotationProcessor.getCloneCallbackParameters(original, method);
				method.setAccessible(true);
				method.invoke(clone, parameters);
			}
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public AnnotationProcessor getAnnotationProcessor() {
		return annotationProcessor;
	}

	public void setAnnotationProcessor(AnnotationProcessor annotationProcessor) {
		this.annotationProcessor = annotationProcessor;
	}

}
