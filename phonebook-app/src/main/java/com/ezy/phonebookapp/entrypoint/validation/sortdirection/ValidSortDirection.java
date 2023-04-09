package com.ezy.phonebookapp.entrypoint.validation.sortdirection;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SortDirectionValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSortDirection {

    String message() default "Invalid sort direction";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
