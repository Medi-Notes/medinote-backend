package com.medinote.backend.domain.medinote.controller;

import com.medinote.backend.domain.medinote.dto.request.DeleteMedinotesRequest;
import com.medinote.backend.domain.medinote.dto.request.UpdateMedinoteStateRequest;
import com.medinote.backend.domain.medinote.dto.request.UpdateMedinoteTextRequest;
import com.medinote.backend.domain.medinote.dto.request.UpdateSttTextRequest;
import com.medinote.backend.domain.medinote.dto.response.MedinoteResponse;
import com.medinote.backend.global.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface MedinoteApi {
    ResponseEntity<ApiResponse<MedinoteResponse>> createMedinote(MultipartFile audioFile, Principal principal);

    ResponseEntity<ApiResponse<Integer>> updateMedinoteState(UpdateMedinoteStateRequest request, Long medinoteId);

    ResponseEntity<ApiResponse<Integer>> updateSttText(UpdateSttTextRequest request, Long medinoteId);

    ResponseEntity<ApiResponse<Integer>> updateMedinoteText(UpdateMedinoteTextRequest request, Long medinoteId);

    ResponseEntity<ApiResponse<?>> deleteMedinotes(DeleteMedinotesRequest request);
}
