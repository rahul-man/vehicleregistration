package com.wipro.asg.vehicleregistration.validator.annotations;

import com.wipro.asg.vehicleregistration.validator.AlphabetsFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = AlphabetsFieldValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AlphabetsField {

    String message() default "Invalid customer field";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
