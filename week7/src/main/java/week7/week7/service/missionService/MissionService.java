package week7.week7.service.missionService;

import week7.week7.web.dto.MissionRequestDTO;

public interface MissionService {

    void addMission(Long storeId, MissionRequestDTO request);
}
