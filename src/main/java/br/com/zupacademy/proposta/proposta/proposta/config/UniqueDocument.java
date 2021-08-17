package br.com.zupacademy.proposta.proposta.proposta.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UniqueDocumentValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface UniqueDocument {

    String message() default "O valor do campo está repetido";

    Class<?>[] groups() default{ };

    Class<? extends Payload>[] payload() default { };

}
