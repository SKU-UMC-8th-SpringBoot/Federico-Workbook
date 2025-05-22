package week7.week7.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import week7.week7.validation.validator.CategoryExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistCategories {

    String message() default "해당하는 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
