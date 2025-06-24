package com.medinote.backend.domain.medinote.controller;

import com.medinote.backend.domain.medinote.dto.*;
import com.medinote.backend.domain.medinote.service.MedinoteService;
import com.medinote.backend.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

import static com.medinote.backend.global.exception.enums.SuccessType.*;

@RestController
@RequestMapping("/api/v1/medinote")
@RequiredArgsConstructor
public class MedinoteController implements MedinoteApi {
    private final MedinoteService medinoteService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<MedinoteResponse>> createMedinote(@RequestParam("file") MultipartFile audioFile, Principal principal) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        CREATE_MEDINOTE_SUCCESS,
                        medinoteService.createMedinote(audioFile, principal)
                ));
    }

    @PatchMapping("/{medinoteId}/state")
    public ResponseEntity<ApiResponse<Integer>> updateMedinoteState(
            @Valid @RequestBody UpdateMedinoteStateRequest request,
            @PathVariable Long medinoteId) {

        return ResponseEntity.ok(ApiResponse.success(
                UPDATE_MEDINOTE_STATE_SUCCESS, medinoteService.updateMedinoteState(medinoteId, request)
        ));
    }

    @PatchMapping("/{medinoteId}/sttText")
    public ResponseEntity<ApiResponse<Integer>> updateSttText(
            @Valid @RequestBody UpdateSttTextRequest request,
            @PathVariable Long medinoteId) {

        return ResponseEntity.ok(ApiResponse.success(
                UPDATE_STT_TEXT_SUCCESS, medinoteService.updateSttText(medinoteId, request)
        ));
    }

    @PatchMapping("/{medinoteId}/medinoteText")
    public ResponseEntity<ApiResponse<Integer>> updateMedinoteText(
            @Valid @RequestBody UpdateMedinoteTextRequest request,
            @PathVariable Long medinoteId) {

        return ResponseEntity.ok(ApiResponse.success(
                UPDATE_MEDINOTE_TEXT_SUCCESS, medinoteService.updateMedinoteText(medinoteId, request)
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<MedinoteListResponse>> getMedinoteList(Principal principal) {
        return ResponseEntity.ok(ApiResponse.success(
                GET_MEDINOTE_LIST_SUCCESS,
                medinoteService.getMedinoteList(principal)
        ));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> deleteMedinotes(@Valid @RequestBody DeleteMedinotesRequest request) {
        medinoteService.deleteMedinotes(request);

        return ResponseEntity.ok(ApiResponse.success(DELETE_MEDINOTES_SUCCESS));
    }
}
