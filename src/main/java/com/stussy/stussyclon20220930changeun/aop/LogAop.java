package com.stussy.stussyclon20220930changeun.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAop {

    @Pointcut("execution(* com.stussy.stussyclon20220930changeun.api.*Api.*(..))")
    private void pointCut() {}

    @Pointcut("@annotation(com.stussy.stussyclon20220930changeun.aop.annotation.LogAspect)")
    private void annotionPointCut() {}

    @Around("annotionPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        //CodeSignature로 다운캐스팅 가능
        CodeSignature codeSignature = (CodeSignature)joinPoint.getSignature();

        String className = codeSignature.getDeclaringTypeName();
        String methodName = codeSignature.getName();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for(int i =0; i < parameterNames.length; i++){
            log.info("Parameter info {}.{} >>> [{}: {}]", className, methodName, parameterNames[i], args[i]);
        }

        Object result = joinPoint.proceed();

        log.info("<<<< Return >>>> {}.{} >>> [{}: {}]", className, methodName, result);

        return result;
    }

//    //log를 찍을 수 있는 코드
//    private static final Logger LOG = LoggerFactory.getLogger(LogAop.class);
//    위 코드를 대신할 수 있는 어노테이션은 @slf4j이다.

//    public void test() {
//        log.info("test");
//    }

}
