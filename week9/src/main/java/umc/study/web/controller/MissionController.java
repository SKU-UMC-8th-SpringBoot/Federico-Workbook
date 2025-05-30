package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.MissionService.MissionService;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "가게에 미션 추가", description = "storeId에 해당하는 가게에 미션을 추가합니다.")
    @PostMapping("/{storeId}/missions")
    public ResponseEntity<String> addMissionToStore(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionRequestDTO request
    ) {
        missionService.addMission(storeId, request);
        return ResponseEntity.ok("미션 등록 완료");
    }

    // 미션 목록 조회
    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getMissionList(
            @PathVariable Long storeId,
            @RequestParam("page") @Valid @ValidPage Integer page) {

        return ApiResponse.onSuccess(missionService.getMissionsByStore(storeId, page));
    }

    @GetMapping("/members/{memberId}/missions/challenging")
    @Operation(summary = "진행 중인 미션 목록 조회", description = "특정 사용자가 진행 중인 미션을 페이징하여 조회합니다.")
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getChallengingMissions(
            @PathVariable Long memberId,
            @RequestParam("page") @Valid @ValidPage Integer page) {

        return ApiResponse.onSuccess(missionService.getChallengingMissions(memberId, page));
    }
}
