package com.medinote.backend.global.aws.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medinote.backend.global.aws.dto.MedinoteSqsMessage;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SqsService {

    private final SqsTemplate sqsTemplate;
    private final ObjectMapper objectMapper;

    @Value("${spring.cloud.aws.sqs.queue-name}")
    private String medinoteQueueName;

    public void sendMedinoteMessage(Long noteId, String s3Key) {
        MedinoteSqsMessage message = MedinoteSqsMessage.of(noteId, s3Key);

        try {
            String payload = objectMapper.writeValueAsString(message);

            sqsTemplate.send(sendOptions -> sendOptions
                    .queue(medinoteQueueName)
                    .payload(payload));

            log.info("SQS 메시지 전송 완료: {}", payload);

        } catch (JsonProcessingException e) {
            log.error("SQS 메시지 직렬화 실패", e);
            throw new RuntimeException("SQS 메시지 직렬화 실패", e);
        }
    }
}
