package com.customvalidation.sample.validation;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class NotNullIfAnotherFieldHasValueValidator
        implements ConstraintValidator<NotNullIfAnotherFieldHasValue, Object> {
    private String fieldName;
    private String expectedFieldValue;
    private String dependFieldName;

    @Override
    public void initialize(NotNullIfAnotherFieldHasValue annotation) {
        fieldName = annotation.fieldName();
        expectedFieldValue = annotation.fieldValue();
        dependFieldName = annotation.dependFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {
        String fieldValue = "";
        String dependFieldValue = "";
        if (value == null) {
            return true;
        }
        try {
            fieldValue = BeanUtils.getProperty(value, fieldName);
            dependFieldValue = BeanUtils.getProperty(value, dependFieldName);
            return validate(fieldValue, dependFieldValue, ctx);

        } catch (NestedNullException ex) {
            dependFieldValue = StringUtils.isNotBlank(dependFieldValue) ? dependFieldValue : "";
            try {
                return validate(fieldValue, dependFieldValue, ctx);
            } catch (NumberFormatException exception) {
                return false;
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NumberFormatException | NullPointerException ex) {
            return false;
        }
    }

    private boolean validate(String fieldValue,
                             String dependFieldValue, ConstraintValidatorContext ctx) {
        if (!StringUtils.isBlank(fieldValue)) {
             if (expectedFieldValue.equals(fieldValue) && (StringUtils.isBlank(dependFieldValue))) {
                ctx.disableDefaultConstraintViolation();
                ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                        .addNode(dependFieldName)
                        .addConstraintViolation();
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}