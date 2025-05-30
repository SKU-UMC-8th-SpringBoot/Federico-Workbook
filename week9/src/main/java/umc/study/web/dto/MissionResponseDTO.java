package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import umc.study.domain.Mission;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MissionResponseDTO {

    @Getter
    @Builder
    public static class MissionPreviewDTO {  // ✅ static 추가
        private Long id;
        private Integer reward;
        private LocalDate deadline;
        private String missionSpec;

        public static MissionPreviewDTO from(Mission mission) {
            return MissionPreviewDTO.builder()
                    .id(mission.getId())
                    .reward(mission.getReward())
                    .deadline(mission.getDeadline())
                    .missionSpec(mission.getMissionSpec())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class MissionPreviewListDTO {  // ✅ static 추가
        private List<MissionPreviewDTO> missions;
        private boolean isFirst;
        private boolean isLast;
        private int totalPages;
        private long totalElements;

        public static MissionPreviewListDTO from(Page<Mission> missionPage) {
            List<MissionPreviewDTO> content = missionPage.getContent().stream()
                    .map(MissionPreviewDTO::from)
                    .collect(Collectors.toList());

            return MissionPreviewListDTO.builder()
                    .missions(content)
                    .isFirst(missionPage.isFirst())
                    .isLast(missionPage.isLast())
                    .totalPages(missionPage.getTotalPages())
                    .totalElements(missionPage.getTotalElements())
                    .build();
        }
    }
}
