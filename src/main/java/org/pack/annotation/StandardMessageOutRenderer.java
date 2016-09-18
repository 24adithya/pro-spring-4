package org.pack.annotation;

import org.pack.MessageProvider;
import org.pack.MessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageRenderer")
public class StandardMessageOutRenderer implements MessageRenderer {
	@Autowired
	private MessageProvider messageProvider;

	public void render() {
		if (messageProvider == null) {
			throw new RuntimeException(
					"You must set the property messageProvider of class:" + StandardMessageOutRenderer.class.getName());
		}
		System.out.println(messageProvider.getMessage());
	}

	public void setMessageProvider(MessageProvider provider) {
		this.messageProvider = provider;
	}

	public MessageProvider getMessageProvider() {
		return this.messageProvider;
	}
}
