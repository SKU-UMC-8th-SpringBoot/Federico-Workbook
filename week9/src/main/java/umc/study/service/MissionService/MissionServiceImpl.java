package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void addMission(Long storeId, MissionRequestDTO request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));

        Mission mission = Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();

        missionRepository.save(mission);
    }

    public MissionResponseDTO.MissionPreviewListDTO getMissionsByStore(Long storeId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10); // 프론트는 1부터 시작, 내부는 0부터
        Page<Mission> missions = missionRepository.findByStoreId(storeId, pageable);
        return MissionResponseDTO.MissionPreviewListDTO.from(missions);
    }

    // 멤버가 진행중인 미션 목록 조회
    public MissionResponseDTO.MissionPreviewListDTO getChallengingMissions(Long memberId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10); // 프론트는 1부터 시작
        Page<Mission> missions = memberMissionRepository.findChallengingMissionsByMemberId(memberId, pageable);
        return MissionResponseDTO.MissionPreviewListDTO.from(missions);
    }
}
