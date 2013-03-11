package com.innometa.fluidity.cloning.annotations;

import java.lang.reflect.Field;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.innometa.fluidity.cloning.IBeanProvider;
import com.innometa.fluidity.cloning.fieldsetters.FieldSetter;

/**
 * Annotation processor handling all the annotation
 * types that customize the cloning behavior.
 * 
 * @author norbert.madarasz
 *
 */
public class AnnotationProcessor {
	private CloneAnnotationProcessor cloneAnnotationProcessor;
	private PostCloneCallbackProcessor postCloneCallbackProcessor;
	private CloneInjectAnnotationProcessor cloneInjectAnnotationProcessor;
	
	public AnnotationProcessor(IBeanProvider injectableBeanProvider) {
		this.cloneAnnotationProcessor = new CloneAnnotationProcessor();
		this.postCloneCallbackProcessor = new PostCloneCallbackProcessor();
		this.cloneInjectAnnotationProcessor = new CloneInjectAnnotationProcessor(injectableBeanProvider);
	}
	
	public FieldSetter getFieldSetter(Field field,
			Set<Class<?>> activationGroups) {
		return cloneAnnotationProcessor.getFieldSetter(field, activationGroups);
	}
	
	public Map<Class<?>, FieldSetter> getFieldSetters(Field field){
		return cloneAnnotationProcessor.getFieldSetters(field);
	}
	
	public List<Method> getPostCloneCallbacks(Object o, Set<Class<?>> activationGroups){
		return postCloneCallbackProcessor.getPostCloneCallbacks(o, activationGroups);
	}

	public <T> T[] getCloneCallbackParameters(T original, Method method) {
		return cloneInjectAnnotationProcessor.getCloneCallbackParameters(original, method);
	}
	
	public CloneAnnotationProcessor getCloneAnnotationProcessor() {
		return cloneAnnotationProcessor;
	}

	public void setCloneAnnotationProcessor(
			CloneAnnotationProcessor cloneAnnotationProcessor) {
		this.cloneAnnotationProcessor = cloneAnnotationProcessor;
	}

	public PostCloneCallbackProcessor getPostCloneCallbackProcessor() {
		return postCloneCallbackProcessor;
	}

	public void setPostCloneCallbackProcessor(
			PostCloneCallbackProcessor postCloneCallbackProcessor) {
		this.postCloneCallbackProcessor = postCloneCallbackProcessor;
	}

	public CloneInjectAnnotationProcessor getCloneInjectAnnotationProcessor() {
		return cloneInjectAnnotationProcessor;
	}

	public void setCloneInjectAnnotationProcessor(
			CloneInjectAnnotationProcessor cloneInjectAnnotationProcessor) {
		this.cloneInjectAnnotationProcessor = cloneInjectAnnotationProcessor;
	}
	
}
