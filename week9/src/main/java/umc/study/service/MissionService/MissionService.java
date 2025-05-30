package umc.study.service.MissionService;

import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

public interface MissionService {

    void addMission(Long storeId, MissionRequestDTO request);

    MissionResponseDTO.MissionPreviewListDTO getMissionsByStore(Long storeId, int page);

    MissionResponseDTO.MissionPreviewListDTO getChallengingMissions(Long memberId, int page);
}