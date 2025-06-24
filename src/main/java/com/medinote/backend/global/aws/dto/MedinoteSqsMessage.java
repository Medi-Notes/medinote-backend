package com.medinote.backend.global.aws.dto;

public record MedinoteSqsMessage(String eventType, Long medinoteId, String s3Key)
{
    public static MedinoteSqsMessage of(Long medinoteId, String s3Key) {
        return new MedinoteSqsMessage("MEDINOTE_TRANSFORM_REQUEST",medinoteId, s3Key);
    }
}
