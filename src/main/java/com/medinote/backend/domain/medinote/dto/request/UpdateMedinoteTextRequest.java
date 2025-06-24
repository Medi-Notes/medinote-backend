package com.medinote.backend.domain.medinote.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record UpdateMedinoteTextRequest(
        @NotNull(message = "medinoteText는 필수입니다.")
        @JsonProperty(required = true)
        String medinoteText) {
}
