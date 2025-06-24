package com.medinote.backend.domain.medinote.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.medinote.backend.domain.medinote.entity.MedinoteState;
import jakarta.validation.constraints.NotNull;

public record UpdateMedinoteStateRequest(
        @NotNull(message = "메디노트 상태는 필수 입니다.")
        @JsonProperty(required = true)
        MedinoteState medinoteState) {
}
