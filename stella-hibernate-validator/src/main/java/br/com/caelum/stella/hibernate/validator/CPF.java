package br.com.caelum.stella.hibernate.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

import br.com.caelum.stella.hibernate.validator.logic.StellaCPFValidator;

/**
 * Restrição que pode ser associada a objetos em que o método
 * {@linkplain #toString()} represente um CPF.
 * 
 * @author Fabio Kung
 * @author Leonardo Bessa
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target( { FIELD, METHOD })
@ValidatorClass(StellaCPFValidator.class)
public @interface CPF {
    String message() default "{cpf_invalid}";

    boolean formatted() default false;
}
