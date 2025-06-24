package com.medinote.backend.domain.medinote.controller;

import com.medinote.backend.domain.medinote.dto.UpdateMedinoteStateRequest;
import com.medinote.backend.domain.medinote.entity.Medinote;
import com.medinote.backend.global.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface MedinoteApi {
    ResponseEntity<ApiResponse<Medinote>> createMedinote(MultipartFile audioFile, Principal principal);

    ResponseEntity<ApiResponse<?>> updateMedinoteState(UpdateMedinoteStateRequest request, Long medinoteId);
}
