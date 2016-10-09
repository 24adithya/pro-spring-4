package org.pack;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DeclareSpringComponents {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();

		/*
		 * Reading from either xml configuration files work since, the
		 * 'interfaces' i.e. providers and renderers are defined in common
		 * package i.e. org.pack
		 */
//		ctx.load("classpath:META-INF/spring/app-context-xml.xml");// This works
		 ctx.load("classpath:META-INF/spring/app-context-annotation.xml");//This
		// also works
		ctx.refresh();
		/*MessageProvider messageProvider = ctx.getBean("messageProvider", MessageProvider.class);
		System.out.println(messageProvider.getMessage());*/

		MessageRenderer messageRenderer = ctx.getBean("messageRenderer", MessageRenderer.class);
		messageRenderer.render();
		
		MessageProvider confMessageProvider = ctx.getBean("confMessageProvider", MessageProvider.class);
		System.out.println(confMessageProvider.getMessage());
		
	}
}
