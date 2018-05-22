package org.pack.services;

public class ServiceRequestDTO<T> {

	private T dto;
	private Object[] parameters;
	private String serviceName;
	private String serviceMethod;
	
	public ServiceRequestDTO(Object[] parameters, String serviceName, String serviceMethod, T dto) {
		super();
		this.parameters = parameters;
		this.serviceName = serviceName;
		this.serviceMethod = serviceMethod;
	}
	
	public Object[] getParameters() {
		return parameters;
	}
	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceMethod() {
		return serviceMethod;
	}
	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}

	public T getDto() {
		return dto;
	}

	public void setDto(T dto) {
		this.dto = dto;
	}
}