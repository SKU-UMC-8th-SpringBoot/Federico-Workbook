package week7.week7.service.missionService;

import week7.week7.domain.Mission;
import week7.week7.web.dto.MemberMissionRequestDTO;

public interface MemberMissionService {

    void challengeMission(MemberMissionRequestDTO request);
}
