package org.pack.services.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pack.services.ServiceRequestDTO;
import org.pack.services.SpringService;
import org.pack.services.dto.AbstractDTO;

public class SpringServiceImpl implements SpringService {

	private static final Log LOG = LogFactory.getLog(SpringService.class);
	
	@Override
	public Object processRequest(ServiceRequestDTO<? extends AbstractDTO> serviceRequestDTO) {
		if(serviceRequestDTO != null) {
			
			try {
				Class clazz = Class.forName(serviceRequestDTO.getServiceName());
				Object obj = clazz.newInstance();
				Method methodToInvoke = clazz.getDeclaredMethod(serviceRequestDTO.getServiceMethod(), ServiceRequestDTO.class);
				methodToInvoke.invoke(obj, serviceRequestDTO);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | InstantiationException e) {
				LOG.error(e.getMessage());
			}
		}
		return null;
	}

}
