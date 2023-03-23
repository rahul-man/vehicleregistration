package com.wipro.asg.vehicleregistration.validator.annotations;

import com.wipro.asg.vehicleregistration.validator.AlphanumericFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlphanumericFieldValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AlphanumericField {

    String message() default "Invalid customer field";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 0;

    int max() default Integer.MAX_VALUE;
}
