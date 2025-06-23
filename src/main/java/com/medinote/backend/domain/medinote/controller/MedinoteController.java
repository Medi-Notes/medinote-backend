package com.medinote.backend.domain.medinote.controller;

import com.medinote.backend.domain.medinote.service.MedinoteService;
import com.medinote.backend.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

import static com.medinote.backend.global.exception.enums.SuccessType.CREATE_MEDINOTE_SUCCESS;

@RestController
@RequestMapping("/api/v1/medinote")
@RequiredArgsConstructor
public class MedinoteController implements MedinoteApi {
    private final MedinoteService medinoteService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<String>> createMedinote(@RequestParam("file") MultipartFile audioFile, Principal principal) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        CREATE_MEDINOTE_SUCCESS,
                        medinoteService.createMedinote(audioFile, principal)
                ));
    }
}
