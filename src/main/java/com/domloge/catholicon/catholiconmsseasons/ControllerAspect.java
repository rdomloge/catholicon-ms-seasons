package com.domloge.catholicon.catholiconmsseasons;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class ControllerAspect {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
	
	
    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}


    @Pointcut("within(*..*Controller)")
    public void allControllerMethods() { }
    

    @Around("publicMethod() && allControllerMethods()")
    public Object repoMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
    	LOGGER.info("Controller called");
    	StopWatch sw = new StopWatch();
    	sw.start();
        Object result = joinPoint.proceed();
        sw.stop();
        LOGGER.debug("Controller finished in {}ms and returning {}", sw.getLastTaskTimeMillis(), result);
        return result;
    }
}