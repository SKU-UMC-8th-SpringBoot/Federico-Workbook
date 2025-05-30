package umc.study.web.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MissionRequestDTO {

    @NotNull(message = "보상 금액은 필수입니다.")
    @Min(value = 500, message = "보상은 최소 1 이상이어야 합니다.")
    private Integer reward;

    @NotNull(message = "마감일은 필수입니다.")
    @Future(message = "마감일은 미래여야 합니다.")
    private LocalDate deadline;

    @NotBlank(message = "미션 설명은 필수입니다.")
    private String missionSpec;
}
