package com.developer.user.ws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//@Component
public class SpringApplicationContext implements ApplicationContextAware {
	private static ApplicationContext CONTEXT;
	

    public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;
	}

    public static Object getBean(String string) {
//		checkApplicationContext();
		return CONTEXT.getBean(string);
	}
	
	private static void checkApplicationContext() {  
        if (CONTEXT == null) {  
            throw new IllegalStateException("CONTEXT not find");  
        }  
    }
}
