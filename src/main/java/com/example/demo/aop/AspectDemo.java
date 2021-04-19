package com.example.demo.aop;

import com.example.demo.event.AggregateRoot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AspectDemo {

    private final ApplicationEventPublisher publisher;

    @Before("execution(* com.example.demo.users.infra.web.UserController.*(..))")
    public void logController(JoinPoint joinPoint) {
        var className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        var methodName = joinPoint.getSignature().getName();

        log.info("BEFORE execution of {}.{}(..)", className, methodName);
    }

    @Around("@annotation(org.springframework.context.event.EventListener)")
    public Object logEventListener(ProceedingJoinPoint joinPoint) throws Throwable {
        var className = joinPoint.getSignature().getDeclaringType().getSimpleName();

        log.info("<<< Before joinPoint execution of class {} >>>", className);
        var result = joinPoint.proceed(joinPoint.getArgs());
        log.info("<<< After joinPoint execution of class {} >>>", className);

        return result;
    }

    @After("@annotation(com.example.demo.event.PublishDomainEvents)")
    public void publishDomainEvents(JoinPoint joinPoint) {
        var args = joinPoint.getArgs();
        if (args.length != 1) {
            throw new IllegalStateException("Cannot publish domain event when at least one parameter is not present !");
        }

        var parameter = args[0];

        if (!(parameter instanceof AggregateRoot)) {
            throw new IllegalStateException("Cannot publish domain event when domain do not implement AggregateRoot !");
        }

        var aggregateRoot = (AggregateRoot) parameter;
        aggregateRoot.domainEvents().forEach(publisher::publishEvent);
        aggregateRoot.clearEvents();
    }

//    @After("execution(* com..*(..))")
//    public void logAfter(JoinPoint joinPoint) {
//        var className = joinPoint.getSignature().getDeclaringType().getSimpleName();
//        var methodName = joinPoint.getSignature().getName();
//
//        log.info("AFTER execution of {}.{}(..)", className, methodName);
//    }
}
