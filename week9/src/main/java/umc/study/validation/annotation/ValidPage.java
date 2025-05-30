package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import umc.study.validation.validator.PageValidator;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER}) // 이 어노테이션은 메서드의 파라미터에 사용됨
@Retention(RetentionPolicy.RUNTIME) // 런타임까지 유지되어야 검증에 사용 가능
@Constraint(validatedBy = PageValidator.class) // 이 어노테이션은 PageValidator로 검증됨
@Documented // Javadoc에 표시되도록 함
public @interface ValidPage {
}
