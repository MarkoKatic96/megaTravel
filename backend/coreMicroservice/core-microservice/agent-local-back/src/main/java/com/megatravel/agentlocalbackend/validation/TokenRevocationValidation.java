package com.megatravel.agentlocalbackend.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Payload;

@Documented
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenRevocationValidation {
	
	String message() default "Potrebno je uneti validnu email adresu";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}