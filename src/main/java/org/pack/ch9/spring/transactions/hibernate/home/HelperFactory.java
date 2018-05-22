package org.pack.ch9.spring.transactions.hibernate.home;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.support.GenericXmlApplicationContext;

public enum HelperFactory {

	INSTANCE;
	
	private final Map<String, SpringBean> helperMap = new ConcurrentHashMap<>();
	
	public SpringBean getBean(String serviceName) {
		SpringBean bean = null;
		if(helperMap.get(serviceName) == null) {
			GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
			ctx.load("classpath:META-INF/spring/app-context-annotation-data-transactions-hibernate-home-xml.xml");
			ctx.refresh();
			ctx.getBean(serviceName, serviceName.getClass());
			bean = (SpringBean) ctx.getBean(serviceName, SpringBean.class);
			helperMap.put(serviceName, bean);
			
			ctx.close();
		} else {
			bean = helperMap.get(serviceName);
		}
		
		return bean;
	}
}
