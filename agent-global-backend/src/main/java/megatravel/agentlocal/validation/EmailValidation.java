package megatravel.agentlocal.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
	
	String message() default "Potrebno je uneti validnu email adresu";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}