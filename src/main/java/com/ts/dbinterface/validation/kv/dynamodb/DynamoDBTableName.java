package com.ts.dbinterface.validation.kv.dynamodb;

import com.ts.dbinterface.validation.kv.dynamodb.validator.DynamoDBTableValidator;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DynamoDBTableValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamoDBTableName {
    String message() default "Invalid field value. Only 0-9, a-z, A-Z characters are allowed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
