/**
 * The ExceptionHandlingAspect class is an aspect for handling exceptions thrown in methods within the Slovakia Got Talent application.
 * It prints information about the exception to the standard error output.
 */
package com.example.skgottalent.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.JoinPoint;

@Aspect
public class ExceptionHandlingAspect {

    /**
     * Handles exceptions thrown in methods within the application.
     *
     * @param joinPoint the join point representing the method where the exception occurred
     * @param ex the RuntimeException thrown
     */
    @AfterThrowing(pointcut = "execution(* com.example.skgottalent..*(..))", throwing = "ex")
    public void handleException(JoinPoint joinPoint, RuntimeException ex) {
        System.err.println("Exception in " + joinPoint.getSignature().getName() + ": " + ex.getMessage());
    }
}
