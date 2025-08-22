package com.medinote.backend.domain.medinote.dto.response;

import java.net.URL;

public record PresignedUrlResponse(URL presignedUrl) {
    public static PresignedUrlResponse of(URL url) {
        return new PresignedUrlResponse(url);
    }
}
