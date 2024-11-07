package com.ts.dbinterface.validation.document.mongodb.validator;

import com.ts.dbinterface.validation.document.documentdb.DocumentDBCollectionName;
import com.ts.dbinterface.validation.document.mongodb.MongoDBCollectionName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MongoDBCollectionValidator implements ConstraintValidator<MongoDBCollectionName, String> {

    public static final String VALID_PATTERN = "^[0-9a-zA-Z_-]*$";

    @Override
    public void initialize(MongoDBCollectionName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.matches(VALID_PATTERN);
    }
}
