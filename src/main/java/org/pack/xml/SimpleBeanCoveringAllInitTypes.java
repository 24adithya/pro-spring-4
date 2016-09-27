package org.pack.xml;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SimpleBeanCoveringAllInitTypes implements InitializingBean {
	private static final String DEFAULT_NAME = "Luke Skywalker";
	private String name;
	private int age = Integer.MIN_VALUE;

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-bean-init-types-all.xml");
		ctx.refresh();
		SimpleBeanCoveringAllInitTypes simpleBean1 = getBean("simpleBean1", ctx);
		SimpleBeanCoveringAllInitTypes simpleBean2 = getBean("simpleBean2", ctx);
		SimpleBeanCoveringAllInitTypes simpleBean3 = getBean("simpleBean3", ctx);
	}
	
	private static SimpleBeanCoveringAllInitTypes getBean(String beanName, ApplicationContext ctx) {
		try {
			SimpleBeanCoveringAllInitTypes bean = (SimpleBeanCoveringAllInitTypes) ctx.getBean(beanName);
			System.out.println(bean);
			return bean;
		} catch (BeanCreationException ex) {
			System.out.println("An error occured in bean configuration: "
					+ ex.getMessage());
			return null;
		}
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("Initializing bean via 'afterPropertiesSet' interface implementation");
		if (name == null) {
			System.out.println("Using default name");
			name = DEFAULT_NAME;
		}
		if (age == Integer.MIN_VALUE) {
			throw new IllegalArgumentException(
					"You must set the age property of any beans of type "
							+ SimpleBean.class);
		}
	}

	public String toString() {
		return "Name: " + name + "\nAge: " + age;
	}
	
	@PostConstruct
	public void initViaAnnotation() {
		System.out.println("Initializing bean via 'PostConstruct' annotation");
		if (name == null) {
			System.out.println("Using default name");
			name = DEFAULT_NAME;
		}
		if (age == Integer.MIN_VALUE) {
			throw new IllegalArgumentException(
					"You must set the age property of any beans of type "
							+ SimpleBean.class);
		}
	}
	
	public void initViaXML() {
		System.out.println("Initializing bean via 'init-method' XML");
		if (name == null) {
			System.out.println("Using default name");
			name = DEFAULT_NAME;
		}
		if (age == Integer.MIN_VALUE) {
			throw new IllegalArgumentException(
					"You must set the age property of any beans of type "
							+ SimpleBean.class);
		}
	}
}
