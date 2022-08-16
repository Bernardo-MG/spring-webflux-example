
package com.bernardomg.example.webflux.test.configuration.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.bernardomg.example.webflux.Application;

@SpringJUnitConfig
@SpringBootTest(classes = Application.class)
@Rollback
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IntegrationTest {

}
