package com.ccfe.property.http.annotation;

import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Document
public @interface PropertyUpdatedLog {

    String value();

    Class clazz();

}
