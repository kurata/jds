package br.com.aqueteron.jds.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface SimpleDomain {

    String pathId() default "";

    String idMethod() default "ordinal";

    String keyMethod() default "name";

}
