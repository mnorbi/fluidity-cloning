package com.innometa.fluidity.cloning.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.innometa.fluidity.cloning.org.springframework.util.ReflectionUtils;
import com.innometa.fluidity.cloning.org.springframework.util.ReflectionUtils.MethodCallback;
import com.innometa.fluidity.cloning.org.springframework.util.ReflectionUtils.MethodFilter;
/**
 * 
 * 
 * @author norbert.madarasz
 *
 */
public class PostCloneCallbackProcessor {

	private final Map<Class<?>, List<Method>> postCloneCallbacks = new HashMap<Class<?>, List<Method>>();

	public List<Method> getPostCloneCallbacks(Object o, Set<Class<?>> activationGroups){
		List<Method> ret = new ArrayList<Method>();
		if (o != null){
			Class<? extends Object> clazz = o.getClass();
			if (!postCloneCallbacks.containsKey(clazz)) {
				final List<Method> list = new ArrayList<Method>();
				ReflectionUtils.doWithMethods(clazz, new MethodCallback() {
					public void doWith(Method method)
							throws IllegalArgumentException,
							IllegalAccessException {
						list.add(method);
					}
				}, new MethodFilter() {
					public boolean matches(Method method) {
						boolean ret = method.getAnnotation(PostClone.class) != null;
						return ret;
					}
				});
				postCloneCallbacks.put(clazz, list);
			}
			for (Method method : postCloneCallbacks.get(clazz)) {
				Class<?>[] postCloneGroups = method.getAnnotation(PostClone.class).value();
				if (postCloneGroups.length == 0){
					postCloneGroups = new Class<?>[]{DefaultActivationGroup.class};
				}
				if (activationGroups != null && activationGroups.size() > 0){
					for (Class<?> group : postCloneGroups) {
						if (activationGroups.contains(group)){
							ret.add(method);
							break;
						}
					}
				}
			}
		}
		
		return ret;
	}

}
