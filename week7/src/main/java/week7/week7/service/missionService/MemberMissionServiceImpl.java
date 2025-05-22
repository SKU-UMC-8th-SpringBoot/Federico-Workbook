package week7.week7.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import week7.week7.domain.Member;
import week7.week7.domain.MemberMission;
import week7.week7.domain.Mission;
import week7.week7.domain.enums.MissionStatus;
import week7.week7.repository.MemberMissionRepository;
import week7.week7.repository.MemberRepository;
import week7.week7.repository.MissionRepository;
import week7.week7.web.dto.MemberMissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionServiceImpl implements MemberMissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void challengeMission(MemberMissionRequestDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)  // 도전 상태로 시작
                .build();

        memberMissionRepository.save(memberMission);
    }
}
