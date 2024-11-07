package com.ts.dbinterface.validation.document.documentdb;

import com.ts.dbinterface.validation.document.documentdb.validator.DocumentDBClusterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DocumentDBClusterValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentDBClusterName {
    String message() default "Invalid field value. Only 0-9, a-z, A-Z characters are allowed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}