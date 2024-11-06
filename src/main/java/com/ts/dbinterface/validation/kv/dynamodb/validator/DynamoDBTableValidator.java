package com.ts.dbinterface.validation.kv.dynamodb.validator;

import com.ts.dbinterface.validation.kv.dynamodb.DynamoDBTableName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DynamoDBTableValidator implements ConstraintValidator<DynamoDBTableName, String> {

    public static final String VALID_PATTERN = "^[0-9a-zA-Z_-]*$";

    @Override
    public void initialize(DynamoDBTableName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.matches(VALID_PATTERN);
    }
}
