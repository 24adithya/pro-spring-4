package org.pack.ch5;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class DynamicPointCutExample {
	public static void main(String[] args) {
		SampleBean target = new SampleBean();
		
		Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointCut(), new SimpleAdvice());
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(target);
//		pf.addAdvice(new SimpleAdvice());
		pf.addAdvisor(advisor);
		SampleBean proxy = (SampleBean) pf.getProxy();
		
		proxy.foo(1);
		proxy.foo(10);
		proxy.foo(100);
		proxy.bar();
		proxy.bar();
		proxy.bar();
	}
}
