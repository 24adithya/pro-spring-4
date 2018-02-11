package org.pack.ch9.spring.transactions.hibernate.home;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.SerializationUtils;

public class SerializationInterceptor implements MethodInterceptor {
	private static final Log LOG = LogFactory.getLog(SerializationInterceptor.class);
	@Override
	public Object invoke(MethodInvocation methodInv) throws Throwable {
		// Serialize - deserialize the arguments and set those as new arguments for the current method call.
		LOG.info("*****interceptor called for " + methodInv.getMethod().getName());
		Object[] methodArgs = methodInv.getArguments();
		if(methodArgs != null && methodArgs.length > 0) {
			for(int i=0;i<methodArgs.length;i++) {
//				if(methodArgs[i] instanceof BoundObject) {
					methodInv.getArguments()[i] = SerializationUtils.deserialize(SerializationUtils.serialize(methodArgs[i]));
//				}
			}
		}
		
		Object ret = methodInv.proceed();
		
		// Serialize - deserialize the return value.
//		if(ret instanceof BoundObject) {
			ret = SerializationUtils.deserialize(SerializationUtils.serialize(ret));
//		}
		
		return ret;
	}
}
