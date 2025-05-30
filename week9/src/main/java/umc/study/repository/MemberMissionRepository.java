package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT mm.mission FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId AND mm.status = 'CHALLENGING'")
    Page<Mission> findChallengingMissionsByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
