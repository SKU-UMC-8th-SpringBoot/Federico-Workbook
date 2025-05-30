package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberMissionRequestDTO {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "미션 ID는 필수입니다.")
    private Long missionId;
}