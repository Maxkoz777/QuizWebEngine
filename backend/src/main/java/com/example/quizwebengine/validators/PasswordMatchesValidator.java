package com.example.quizwebengine.validators;

import com.example.quizwebengine.annotations.PasswordMatches;
import com.example.quizwebengine.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest request = (SignupRequest) o;
        return request.getPassword().equals(request.getConfirmPassword());
    }
}
