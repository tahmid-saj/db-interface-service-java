package com.ts.dbinterface.validation.relational.rds.validator;

import com.ts.dbinterface.validation.relational.rds.RDSDBName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RDSDBValidator implements ConstraintValidator<RDSDBName, String> {

    public static final String VALID_PATTERN = "^[0-9a-zA-Z_-]*$";

    @Override
    public void initialize(RDSDBName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.matches(VALID_PATTERN);
    }
}
