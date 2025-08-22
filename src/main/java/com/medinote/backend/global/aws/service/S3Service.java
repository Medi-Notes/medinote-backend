package com.medinote.backend.global.aws.service;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;
    private final S3Template s3Template;

    private static final String S3_KEY_PREFIX = "audio";
    private static final String S3_ORIGINAL_PREFIX = "original/audio";

    @Value("${spring.cloud.aws.s3.bucket-name}")
    private String bucketName;

    /**
     * S3 업로드
     */
    public void upload(MultipartFile file, String key) throws IOException {
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
    }

    /**
     * S3 삭제
     */
    public void delete(String key) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.deleteObject(deleteRequest);
    }

    public String generateS3Key(Long memberId, String fileName) {
        return S3_KEY_PREFIX + "/" + memberId + "/" + UUID.randomUUID() + "-" + fileName;
    }

    public String generateOriginalS3Key(Long memberId, String fileName) {
        return S3_ORIGINAL_PREFIX + "/" + memberId + "/" + UUID.randomUUID().toString().substring(0,6) + "-" + fileName;
    }

    public URL getPresignedURL(Long memberId, String fileName) {
        return s3Template.createSignedPutURL(
                bucketName,
                generateOriginalS3Key(memberId, fileName),
                Duration.ofMinutes(5)
        );
    }
}
