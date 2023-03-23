package com.wipro.asg.vehicleregistration.validator.annotations;

import com.wipro.asg.vehicleregistration.validator.GenderFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderFieldValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {

    String message() default "Invalid customer field";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

