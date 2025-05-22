package week7.week7.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week7.week7.service.storeService.StoreService;
import week7.week7.web.dto.StoreRequestDTO;


@Tag(name = "Store API" , description = "가게 관련API (등록) ")
@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @Operation(
            summary = "특정 지역에 가게 등록",
            description = "지역 ID에 해당하는 지역에 새로운 가게를 등록합니다."
    )
    @PostMapping("/{regionId}/stores")
    public ResponseEntity<String> addStoreToRegion(

            @Parameter(description = "지역 아이디" , example = "1")
            @PathVariable Long regionId,

            @Parameter(description = "등록할 가게 정보")
            @RequestBody @Valid StoreRequestDTO request
    ) {
        storeService.addStore(regionId, request);
        return ResponseEntity.ok("가게 등록 완료");
    }
}