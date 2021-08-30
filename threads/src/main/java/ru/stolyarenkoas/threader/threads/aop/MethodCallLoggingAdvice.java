package ru.stolyarenkoas.threader.threads.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * Advice responsible for logging method calls.
 */
@Log4j2
@Aspect
@Component
public class MethodCallLoggingAdvice {

    /**
     * All methods logging.
     * @FIXME: development process method.
     */
    @Before("within(ru.stolyarenkoas.threader.threads..*)")
    public void logMethodCall(@Nonnull final JoinPoint joinPoint) {
        final String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        final String methodName = joinPoint.getSignature().getName();
        log.info("[Method call]: {} -> {}", className, methodName);
    }

}
