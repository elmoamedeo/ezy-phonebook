package com.ezy.phonebookapp.entrypoint.validation.sortby;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SortByValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSortBy {

    String message() default "Invalid sort field";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
