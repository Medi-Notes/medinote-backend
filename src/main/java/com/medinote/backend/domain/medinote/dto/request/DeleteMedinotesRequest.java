package com.medinote.backend.domain.medinote.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DeleteMedinotesRequest(
        @NotNull(message = "삭제할 메디노트 리스트는 필수 입니다.")
        @JsonProperty(required = true)
        List<Long> medinoteIdList
) {
}
