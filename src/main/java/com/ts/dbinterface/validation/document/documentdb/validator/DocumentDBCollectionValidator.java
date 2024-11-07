package com.ts.dbinterface.validation.document.documentdb.validator;

import com.ts.dbinterface.validation.document.documentdb.DocumentDBCollectionName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DocumentDBCollectionValidator implements ConstraintValidator<DocumentDBCollectionName, String> {

    public static final String VALID_PATTERN = "^[0-9a-zA-Z_-]*$";

    @Override
    public void initialize(DocumentDBCollectionName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.matches(VALID_PATTERN);
    }
}
