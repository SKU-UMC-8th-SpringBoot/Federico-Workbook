package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.study.validation.annotation.ValidPage;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {
    //ConstraintValidator<A extends Annotation, T>
    // .. A는 어떤 어노테이션을 검사할지, T는 검사 대상 타입을 의미
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value >= 1; // null이 아니고 1이상이어야 유효하다고 판단
    }
}

