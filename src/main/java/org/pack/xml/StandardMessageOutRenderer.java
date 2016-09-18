package org.pack.xml;

import org.pack.MessageProvider;
import org.pack.MessageRenderer;

public class StandardMessageOutRenderer implements MessageRenderer {
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
