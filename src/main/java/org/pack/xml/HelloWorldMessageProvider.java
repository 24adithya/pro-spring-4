package org.pack.xml;

import org.pack.MessageProvider;

public class HelloWorldMessageProvider implements MessageProvider {
	@Override
	public String getMessage() {
		return "Hello World!";
	}
}
