package com.medinote.backend.domain.medinote.dto.response;

import com.medinote.backend.domain.medinote.entity.Medinote;

import java.util.List;

public record MedinoteListResponse(
        List<MedinoteResponse> medinotes
) {
    public static MedinoteListResponse from(List<Medinote> medinotes) {
        return new MedinoteListResponse(medinotes.stream()
                .map(MedinoteResponse::from)
                .toList());
    }
}
