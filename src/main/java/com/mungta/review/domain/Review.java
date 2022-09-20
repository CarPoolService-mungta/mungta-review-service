package com.mungta.review.domain;

import lombok.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.mungta.review.domain.repository.ReviewRepository;

import java.util.List;
import javax.transaction.Transactional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "REVIEW")
public class Review extends BaseEntity{


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "REVIEWER_ID", nullable = false)
    private String reviewerId;

    @Column(name = "REVIEW_TARGET_ID", nullable = false)
    private String reviewTargetId;

    @Column(name = "PARTY_ID", nullable = false)
    private Long partyId;

    @Enumerated(EnumType.STRING)
    private Role reviewerRole;

    @Enumerated(EnumType.STRING)
    private Role targetRole;

    @Embedded
    private ReviewContents reviewContents;

    @Column(name = "REVIEW_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    @Transient
    private Double scoreAvg;

    public static Review of(String reviewerId, String reviewTargetId, Long partyId, ReviewContents reviewContents, Role reviewerRole, Role targetRole){
        return Review.builder()
                .reviewerId(reviewerId)
                .reviewTargetId(reviewTargetId)
                .partyId(partyId)
                .reviewContents(reviewContents)
                .reviewerRole(reviewerRole)
                .targetRole(targetRole)
                .reviewStatus(ReviewStatus.REGISTERED)
                .build();
    }


    public void modifyReviewContents(ReviewContents reviewContents) {
        this.reviewContents = reviewContents;
    }

}