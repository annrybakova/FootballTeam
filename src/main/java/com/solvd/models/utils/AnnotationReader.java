package com.solvd.models.utils;

import com.solvd.annotations.Info;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationReader {
        private static final Logger logger = LoggerFactory.getLogger(AnnotationReader.class);

    private Class<?> clazz;

    public AnnotationReader(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getClassAuthor() {
        Info info = clazz.getAnnotation(Info.class);
        if (info != null) {
            return info.author();
        }
        return "Unknown";
    }

    public String getClassDate() {
        Info info = clazz.getAnnotation(Info.class);
        if (info != null) {
            return info.date();
        }
        return "Unknown";
    }

    public void printMethodAnnotations() {
        for (Method method : clazz.getDeclaredMethods()) {
            Info info = method.getAnnotation(Info.class);
            if (info != null) {
                logger.info("Method " + method.getName() + " author: " + info.author() + ", date: " + info.date());
            }
        }
    }
}