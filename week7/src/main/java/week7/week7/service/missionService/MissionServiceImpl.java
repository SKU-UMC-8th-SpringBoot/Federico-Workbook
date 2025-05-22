package week7.week7.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import week7.week7.domain.Mission;
import week7.week7.domain.Store;
import week7.week7.repository.MissionRepository;
import week7.week7.repository.StoreRepository;
import week7.week7.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

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
}
