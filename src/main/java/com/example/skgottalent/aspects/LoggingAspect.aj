package com.example.skgottalent.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;

@Aspect
public class LoggingAspect {

    /**
     * Логирует вызов метода addParticipant.
     *
     * @param joinPoint информация о точке в программе, где был вызван аспект
     */
    @Before("execution(* com.example.skgottalent.controllers.MainWindowController.addParticipant(com.example.skgottalent.models.Participant))")
    public void logAddParticipantCall(JoinPoint joinPoint) {
        System.out.println("Adding new participant: " + joinPoint.getArgs()[0]);
    }

    /**
     * Логирует вызов метода addJudge.
     *
     * @param joinPoint информация о точке в программе, где был вызван аспект
     */
    @Before("execution(* com.example.skgottalent.controllers.MainWindowController.addJudge(com.example.skgottalent.models.Judge))")
    public void logAddJudgeCall(JoinPoint joinPoint) {
        System.out.println("Adding new judge: " + joinPoint.getArgs()[0]);
    }
}
