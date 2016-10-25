package org.pack.ch5;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {
	public IsModifiedAdvisor() {
		super(new IsModifiedMixin());
	}
}
