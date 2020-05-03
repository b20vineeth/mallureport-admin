package com.sprhib.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer {


	public static final String RESOURCE_PATH = "file:\\E:\\Project\\Mallureports\\mallureport\\src\\main\\webapp\\WEB-INF\\resource";
	public static final String UPLOAD_PATH = "E:\\Project\\Mallureports\\gallery";
	public static final String PIC_UPLOAD_PATH = "file:\\E:\\Project\\Mallureports\\gallery";
	public static final String DOMAIN = "http://localhost:8081/";
	
	
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebAppConfig.class);
		servletContext.addListener(new ContextLoaderListener(ctx));

		ctx.setServletContext(servletContext);
		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}

}
