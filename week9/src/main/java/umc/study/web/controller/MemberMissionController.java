package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.service.MissionService.MemberMissionService;
import umc.study.web.dto.MemberMissionRequestDTO;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "MemberMission API", description = "회원 미션 도전 API")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @Operation(summary = "회원이 미션에 도전", description = "회원이 특정 미션을 CHALLENGING 상태로 등록합니다.")
    @PostMapping("/missions")
    public ResponseEntity<String> challengeMission(@RequestBody @Valid MemberMissionRequestDTO request) {
        memberMissionService.challengeMission(request);
        return ResponseEntity.ok("미션 도전 완료");
    }


}
