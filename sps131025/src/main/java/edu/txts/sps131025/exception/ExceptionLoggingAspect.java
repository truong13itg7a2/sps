package edu.txts.sps131025.exception;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;

@Slf4j
public class ExceptionLoggingAspect {

	/*10. Best Practices Thực Tế
	Logging Strategy*/
	@Before("@within(org.springframework.web.bind.annotation.RestController)")
	public void logMethodEntry(JoinPoint joinPoint) {
		log.debug("Entering: {}", joinPoint.getSignature().toShortString());
	}
}
