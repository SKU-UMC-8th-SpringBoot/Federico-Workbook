package week7.week7.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week7.week7.service.missionService.MissionService;
import week7.week7.web.dto.MissionRequestDTO;

@RestController
@RequestMapping("/api/stores")
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
}
