package com.medinote.backend.domain.medinote.controller;

import com.medinote.backend.domain.medinote.dto.MedinoteResponse;
import com.medinote.backend.domain.medinote.dto.UpdateMedinoteStateRequest;
import com.medinote.backend.domain.medinote.dto.UpdateSttTextRequest;
import com.medinote.backend.global.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface MedinoteApi {
    ResponseEntity<ApiResponse<MedinoteResponse>> createMedinote(MultipartFile audioFile, Principal principal);

    ResponseEntity<ApiResponse<Integer>> updateMedinoteState(UpdateMedinoteStateRequest request, Long medinoteId);

    ResponseEntity<ApiResponse<Integer>> updateSttText(UpdateSttTextRequest request, Long medinoteId);
}
