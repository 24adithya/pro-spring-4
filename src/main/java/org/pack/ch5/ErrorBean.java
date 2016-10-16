package org.pack.ch5;

import java.io.IOException;

public class ErrorBean {
	public void errorProneMethod() throws Exception {
		throw new Exception("Foo");
	}

	public void otherErrorProneMethod() throws IllegalArgumentException {
		throw new IllegalArgumentException("Bar");
	}
	
	public void ioProneMethod() throws IOException {
		throw new IOException("IO");
	}
}
