package com.medinote.backend.domain.medinote.entity;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MedinoteState {
    PENDING,
    PROCESSING,
    STT_DONE,
    COMPLETED,
    REJECTED;
}
