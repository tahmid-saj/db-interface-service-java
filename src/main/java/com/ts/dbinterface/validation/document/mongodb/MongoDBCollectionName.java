package com.ts.dbinterface.validation.document.mongodb;

import com.ts.dbinterface.validation.document.mongodb.validator.MongoDBCollectionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MongoDBCollectionValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MongoDBCollectionName {
    String message() default "Invalid field value. Only 0-9, a-z, A-Z characters are allowed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
