package com.medinote.backend.domain.medinote.controller;

import com.medinote.backend.domain.medinote.dto.MedinoteResponse;
import com.medinote.backend.domain.medinote.dto.UpdateMedinoteStateRequest;
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

import static com.medinote.backend.global.exception.enums.SuccessType.CREATE_MEDINOTE_SUCCESS;
import static com.medinote.backend.global.exception.enums.SuccessType.UPDATE_MEDINOTE_STATE_SUCCESS;

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

    @PatchMapping("/{medinoteId}")
    public ResponseEntity<ApiResponse<?>> updateMedinoteState(
            @Valid @RequestBody UpdateMedinoteStateRequest request,
            @PathVariable Long medinoteId) {

        return ResponseEntity.ok(ApiResponse.success(
                UPDATE_MEDINOTE_STATE_SUCCESS, medinoteService.updateMedinoteState(medinoteId, request)
        ));
    }
}
