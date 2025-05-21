package week7.week7.apiPayload.exception.handler;

import lombok.Getter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import week7.week7.apiPayload.code.status.ErrorStatus;


@Getter
public class FoodCategoryHandler extends RuntimeException {

    private final ErrorStatus errorStatus;
    public FoodCategoryHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

}
