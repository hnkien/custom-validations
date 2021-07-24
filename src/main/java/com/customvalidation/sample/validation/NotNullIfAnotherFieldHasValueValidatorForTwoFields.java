package com.customvalidation.sample.validation;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class NotNullIfAnotherFieldHasValueValidatorForTwoFields
        implements ConstraintValidator<NotNullIfAnotherFieldHasValueForTwoFieldValidations, Object> {
    private String fieldName1;
    private String fieldName2;
    private String expectedFieldValue1;
    private String expectedFieldValue2;
    private String dependFieldName;

    @Override
    public void initialize(NotNullIfAnotherFieldHasValueForTwoFieldValidations annotation) {
        fieldName1 = annotation.fieldName1();
        fieldName2 = annotation.fieldName2();
        expectedFieldValue1 = annotation.fieldValue1();
        expectedFieldValue2 = annotation.fieldValue2();
        dependFieldName = annotation.dependFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {
        String fieldValue1 = "";
        String fieldValue2 = "";
        String dependFieldValue = "";
        if (value == null) {
            return true;
        }
        try {
            fieldValue1 = BeanUtils.getProperty(value, fieldName1);
            fieldValue2 = BeanUtils.getProperty(value, fieldName2);
            dependFieldValue = BeanUtils.getProperty(value, dependFieldName);
            return validate(fieldValue1, fieldValue2, dependFieldValue, ctx);

        } catch (NestedNullException ex) {
            dependFieldValue = StringUtils.isNotBlank(dependFieldValue) ? dependFieldValue : "";
            return validate(fieldValue1, fieldValue2, dependFieldValue, ctx);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NullPointerException ex) {
            return false;
        }
    }

    private boolean validate(String fieldValue1, String fieldValue2, String dependFieldValue, ConstraintValidatorContext ctx) {
        if (expectedFieldValue1.equals(fieldValue1) && expectedFieldValue2.equals(fieldValue2) && (StringUtils.isBlank(dependFieldValue))) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                    .addNode(dependFieldName)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
