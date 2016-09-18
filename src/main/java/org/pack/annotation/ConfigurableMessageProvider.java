package org.pack.annotation;

import org.pack.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("confMessageProvider")
public class ConfigurableMessageProvider implements MessageProvider {
	private String message;

	/*@Autowired
	public ConfigurableMessageProvider(@Value("Configurable message") String message) {
		this.message = message;
	}*/
	
	@Autowired
	public ConfigurableMessageProvider(String confMmessage) {
		this.message = confMmessage;
	}

	public String getMessage() {
		return this.message;
	}
}
