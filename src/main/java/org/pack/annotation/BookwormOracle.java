package org.pack.annotation;

import org.pack.Oracle;
import org.springframework.stereotype.Service;

@Service("oracle")
public class BookwormOracle implements Oracle{

	public String defineMeaningOfLife() {
		return "Encyclopedias are a waste of money - use the Internet";
	}
}
