package org.pack;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class XmlConfigWithBeanFactory {
	public static void main(String[] args) {
		
		//Way 1
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader rdr = new XmlBeanDefinitionReader(factory);
		rdr.loadBeanDefinitions(new ClassPathResource("META-INF/spring/xml-bean-factory-config.xml"));
		Oracle oracle = (Oracle) factory.getBean("oracle");
		System.out.println(oracle.defineMeaningOfLife());
		
		oracle = null;
		
		/*
		 * Way 2 - preferred approach : since, application context provides
		 * support for transaction and aop service,message source for
		 * internationalization (i18n), and application event handling, to name
		 * a few
		 */
		 
		ApplicationContext appContext = new ClassPathXmlApplicationContext("META-INF/spring/xml-bean-factory-config.xml");
		oracle = (Oracle) appContext.getBean("oracle");
		System.out.println(oracle.defineMeaningOfLife());
	}
}
