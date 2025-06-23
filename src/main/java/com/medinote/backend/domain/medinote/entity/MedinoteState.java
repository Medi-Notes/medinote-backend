package com.medinote.backend.domain.medinote.entity;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MedinoteState {
    PENDING,
    PROCESSING,
    FINISH,
    REJECTED;
}
