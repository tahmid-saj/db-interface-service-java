package com.ts.dbinterface.validation.kv.dynamodb.validator;

import com.ts.dbinterface.validation.kv.dynamodb.DynamoDBKey;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DynamoDBKeyValidator implements ConstraintValidator<DynamoDBKey, String> {

    public static final String VALID_PATTERN = "^[0-9a-zA-Z_-]*$";

    @Override
    public void initialize(DynamoDBKey constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.matches(VALID_PATTERN);
    }
}
