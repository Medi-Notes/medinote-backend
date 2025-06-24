package com.medinote.backend.global.aws.dto;

public record MedinoteSqsMessage(Long medinoteId, String s3Key)
{
    public static MedinoteSqsMessage of(Long medinoteId, String s3Key) {
        return new MedinoteSqsMessage(medinoteId, s3Key);
    }
}
