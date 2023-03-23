package com.wipro.asg.vehicleregistration.validator;

import com.wipro.asg.vehicleregistration.validator.annotations.Gender;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.wipro.asg.vehicleregistration.model.constants.Constants.ALPHABETS;

public class GenderFieldValidator implements ConstraintValidator<Gender, String> {

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isNotEmpty(field) && field.matches(ALPHABETS.getCode()) && field.length() <= 8;
    }
}

