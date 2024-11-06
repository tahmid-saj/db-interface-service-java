package com.ts.dbinterface.validation.kv.dynamodb;

import com.ts.dbinterface.validation.kv.dynamodb.validator.DynamoDBValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DynamoDBValueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamoDBValue {
    String message() default "Invalid field value. Only 0-9, a-z, A-Z characters are allowed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
