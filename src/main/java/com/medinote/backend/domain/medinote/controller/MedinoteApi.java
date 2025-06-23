package com.medinote.backend.domain.medinote.controller;

import com.medinote.backend.global.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface MedinoteApi {
    ResponseEntity<ApiResponse<String>> createMedinote(MultipartFile audioFile, Principal principal);
}
