package br.com.goblin.web.faces;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

public class FacesUtils {

	@SuppressWarnings("unchecked")
	public static <T> T findBean(String beanName) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}

	public static void addFlashParam(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(key, value);		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getFlashParam(String key) {
		return (T) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(key);
	}

	public static String executeAction(String bean, String method) {
		return executeAction(bean, method, new Object[] {});
	}

	public static String executeAction(String bean, String method, Object[] parameters) {
	    List<Class<?>> classes = new ArrayList<>(parameters.length);
	    
	    for (Object p : parameters) {
	    	classes.add(p.getClass());
	    }
	    
	    Class<?>[] classParameters = classes.toArray(new Class[]{});
	    
	    try {
	    	Object obean = FacesUtils.findBean(bean);
			Method m = obean.getClass().getDeclaredMethod(method, classParameters);
			return (String) m.invoke(obean, parameters);
			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	    
	}
}
