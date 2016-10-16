package org.pack.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class HelloWorldAOPExample {
	public static void main(String[] args) {
		MessageWriter target = new MessageWriter();
		target.writeMessage();
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new MessageDecorator());
		pf.setTarget(target);
		
		System.out.println("");
		
		MessageWriter proxy = (MessageWriter) pf.getProxy();
		proxy.writeMessage();
	}
}
