package com.wipro.asg.vehicleregistration.validator;

import com.wipro.asg.vehicleregistration.validator.annotations.AlphanumericField;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.wipro.asg.vehicleregistration.model.constants.Constants.ALPHANUMERIC;

public class AlphanumericFieldValidator implements ConstraintValidator<AlphanumericField, String> {

    private Integer min;
    private Integer max;

    @Override
    public void initialize(AlphanumericField alphanumericField) {
        min = alphanumericField.min();
        max = alphanumericField.max();
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(field) && !field.matches(ALPHANUMERIC.getCode())) {
            return false;
        } else {
            return (min <= 0 && max >= Integer.MAX_VALUE) || (field.length() >= min && field.length() <= max);
        }
    }
}
