package com.medinote.backend.domain.medinote.dto.response;

import com.medinote.backend.domain.medinote.entity.Medinote;
import com.medinote.backend.domain.medinote.entity.MedinoteState;

import java.time.LocalDateTime;

public record MedinoteResponse(
        Long id,
        String title,
        MedinoteState state,
        String sttText,
        String medinoteText,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static MedinoteResponse from(Medinote medinote) {
        return new MedinoteResponse(
                medinote.getMedinoteId(),
                medinote.getMedinoteTitle(),
                medinote.getMedinoteState(),
                medinote.getSttText(),
                medinote.getMedinoteText(),
                medinote.getCreatedAt(),
                medinote.getUpdatedAt()
        );
    }
}
