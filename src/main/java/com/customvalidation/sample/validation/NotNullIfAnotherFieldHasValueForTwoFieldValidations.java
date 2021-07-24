package com.customvalidation.sample.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NotNullIfAnotherFieldHasValueValidatorForTwoFields.class)
@Documented
public @interface NotNullIfAnotherFieldHasValueForTwoFieldValidations {
    String fieldName1();

    String fieldValue1();

    String fieldName2();

    String fieldValue2();

    String dependFieldName();

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotNullIfAnotherFieldHasValueForTwoFieldValidations[] value();
    }
}