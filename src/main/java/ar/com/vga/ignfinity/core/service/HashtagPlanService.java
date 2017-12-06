package ar.com.vga.ignfinity.core.service;

import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface HashtagPlanService {

	void runPlan(Integer minsDelay) throws IgnfinityException;
	
}
