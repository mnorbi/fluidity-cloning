package com.innometa.fluidity.cloning.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.innometa.fluidity.cloning.IBeanProvider;

/**
 * 
 * @author norbert.madarasz
 *
 */
public class CloneInjectAnnotationProcessor {
	private IBeanProvider beanProvider;

	public CloneInjectAnnotationProcessor(IBeanProvider beanProvider) {
		this.beanProvider = beanProvider;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] getCloneCallbackParameters(T original, Method method) {
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		Class<?>[] parameterTypes = method.getParameterTypes();
		List<Object> parameters = new ArrayList<Object>();
		int i = 0;
		for (Annotation[] annotations : parameterAnnotations) {
			CloneInject cloneInjectAnnotation = getCloneInjectAnnotation(annotations);
			Class<?> parameterType = parameterTypes[i];
			if (cloneInjectAnnotation != null){
				parameters.add(injectableImplementationFor(parameterType, cloneInjectAnnotation));
			} else {
				if(!parameterType.equals(original.getClass())){
					throw new IllegalArgumentException(
							String.format("Invalid post clone callback parameter at idx[%i], type should be[%s].", i, original.getClass().getName()));
				}
				parameters.add(original);
			}
			
			i++;
		}
		return (T[]) parameters.toArray();
	}

	public Object injectableImplementationFor(Class<?> parameterType, CloneInject cloneInjectAnnotation) {
		Object ret = null;
		if (cloneInjectAnnotation != null && cloneInjectAnnotation.value() != null && !cloneInjectAnnotation.value().equals("")){
			ret = beanProvider.getBean(cloneInjectAnnotation.value(), parameterType);
		} else {
			ret = beanProvider.getBean(parameterType);
		}
		return ret;
	}

	public CloneInject getCloneInjectAnnotation(Annotation[] annotations) {
		CloneInject ret = null;
		for (Annotation annotation : annotations) {
			if (annotation.annotationType().equals(CloneInject.class)){
				ret = (CloneInject) annotation;
				break;
			}
		}
		return ret;
	}

}
