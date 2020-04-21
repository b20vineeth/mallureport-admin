package com.sprhib.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.reflections.Reflections;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.TaskUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.PathMatcher;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.easypick.framework.utility.commonUtility.EventListener;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.controller.Event;
import com.easypick.web.events.vo.KeyData;

@Configuration
@ComponentScan("com")
@EnableWebMvc
@EnableTransactionManagement
@EnableScheduling
public class WebAppConfig extends WebMvcConfigurerAdapter {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	private static final String PROPERTY_CURRENT_SESSION_CONTEXT = "hibernate.jdbc.lob.non_contextual_creation";
	private static final String PROPERTY_NON_CONTEXTUAL = "hibernate.current_session_context_class";
	private static final String FILE_PATH = "file:///E:/Project/Mallureports/mallureport/src/main/webapp/WEB-INF/resource/";

	@Resource
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/easypick01?autoReconnect=true&amp;useUnicode=true&amp;createDatabaseIfNotExist=true&amp;characterEncoding=utf-8");
		dataSource.setUsername("root");
		dataSource.setPassword("");

		return dataSource;
	}

	@Bean(name = "applicationEventMulticaster")
	ApplicationEventMulticaster applicationEventMulticaster() {
	    SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
	    eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
	    eventMulticaster.setErrorHandler(TaskUtils.LOG_AND_SUPPRESS_ERROR_HANDLER);
	    return eventMulticaster;
	}
	
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760);
		return multipartResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("locale/label/admin/login", "locale/label/admin/Privilege");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan("com.easypick.admin.entity");
		sessionFactoryBean.setHibernateProperties(hibProperties());
		return sessionFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, "true");

		// properties.setProperty(PROPERTY_CURRENT_SESSION_CONTEXT,
		// env.getRequiredProperty(
		// env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT)));
		properties.setProperty(PROPERTY_NON_CONTEXTUAL, "thread");

		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	public PathMatcher pathMatcher() {
		return new CaseInsensitivePathMatcher();
	}

	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
		handlerMapping.setOrder(0);
		handlerMapping.setPathMatcher(pathMatcher());
		handlerMapping.setUseSuffixPatternMatch(false);
		return handlerMapping;
	}

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		// Using gmail
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("picmass.com@gmail.com");
		mailSender.setPassword("97462122");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");// Prints out everything on
														// screen

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
	
	
	@Bean(name="event")
	public Event getEventListeners() {
	   Map<String,List<KeyData>> map=new HashMap<>();
	   KeyData keydata=null;
	   List<KeyData> value=new ArrayList<>();
	   Event event=new Event();
	   Reflections ref = new Reflections("com.easypick");
		for (Class<?> cl : ref.getTypesAnnotatedWith(EventListener.class)) {
			EventListener findable = cl.getAnnotation(EventListener.class);
			 keydata=new KeyData();
			if(Objects.nonNull(map.get(findable.eventKey())))
			{
				keydata.setEvent(StringUitity.convertToSmallLetter(cl.getSimpleName()));
				keydata.setPriority(Integer.parseInt(findable.priority()));
				value= map.get(findable.eventKey());
				value.add(keydata);
			}
			else{
				keydata.setEvent(StringUitity.convertToSmallLetter(cl.getSimpleName()));
				keydata.setPriority(Integer.parseInt(findable.priority()));
				map.put(findable.eventKey(),Stream.of(keydata).collect(Collectors.toList()));
			}
		 }
		event.setListeners(map);
		return event;
	}
	

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations(Initializer.RESOURCE_PATH+"\\")
				.resourceChain(true).addResolver(new PathResourceResolver());

		registry.addResourceHandler("/pic/**")
				.addResourceLocations(Initializer.PIC_UPLOAD_PATH+"\\images\\")
				.resourceChain(true).addResolver(new PathResourceResolver());
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}
