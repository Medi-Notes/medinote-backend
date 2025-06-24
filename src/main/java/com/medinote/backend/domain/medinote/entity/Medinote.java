package com.medinote.backend.domain.medinote.entity;

import com.medinote.backend.domain.member.entity.Member;
import com.medinote.backend.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Medinote extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medinoteId;

    @NotNull
    @Size(max = 200)
    private String medinoteTitle;

    @Column(columnDefinition = "mediumtext")
    private String medinoteText;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MedinoteState medinoteState;

    @NotNull
    private String s3Key;

    @Column(columnDefinition = "mediumtext")
    private String sttText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member memberId;
}
