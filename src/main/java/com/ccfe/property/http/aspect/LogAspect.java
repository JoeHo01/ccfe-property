package com.ccfe.property.http.aspect;

import com.ccfe.property.http.mvc.dao.MongoSupport;
import com.ccfe.property.http.mvc.entity.DO.LogDO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Aspect
@Component
public class LogAspect {

    final private MongoSupport mongoSupport;

    @Autowired
    public LogAspect(MongoSupport mongoSupport) {
        this.mongoSupport = mongoSupport;
    }

    @Pointcut("@annotation(com.ccfe.property.http.annotation.PropertyUpdatedLog)")
    void propertyUpdated(){}

    @AfterReturning(pointcut = "propertyUpdated()", returning = "result")
    public synchronized void log(JoinPoint joinPoint, Object result){
        LogDO logDO = new LogDO();
        Map<String, String> updateMethod = new HashMap<>();
        List<Map<String, String>> documents = new ArrayList<>();

        Object[] params = joinPoint.getArgs();
        String idString = params[0].toString();

        updateMethod.put("field", params[1].toString());
        updateMethod.put("value", params[2].toString());

        String[] ids = idString.split(",");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");


    }
}
