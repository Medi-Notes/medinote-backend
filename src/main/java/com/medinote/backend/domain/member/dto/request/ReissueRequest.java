package com.medinote.backend.domain.member.dto.request;

import jakarta.validation.constraints.NotNull;

public record ReissueRequest(
        @NotNull
        String refreshToken) {
}
