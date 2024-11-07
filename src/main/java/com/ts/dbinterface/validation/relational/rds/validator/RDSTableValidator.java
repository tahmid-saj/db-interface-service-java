package com.ts.dbinterface.validation.relational.rds.validator;

import com.ts.dbinterface.validation.relational.rds.RDSTableName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RDSTableValidator implements ConstraintValidator<RDSTableName, String> {

    public static final String VALID_PATTERN = "^[0-9a-zA-Z_-]*$";

    @Override
    public void initialize(RDSTableName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.matches(VALID_PATTERN);
    }
}
