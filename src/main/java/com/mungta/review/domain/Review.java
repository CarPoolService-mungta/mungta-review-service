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
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "REVIEW")

public class Review {



      @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "REVIEWER_ID", nullable = false)
    private String reviewerId;

    @Column(name = "REVIEWER_TARGET_ID", nullable = false)
    private String reviewTargetId;

    @Embedded
    private PartyInfo partyInfo;

    @Embedded
    private ReviewContents reviewContents;

    @Column(name = "REVIEW_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    @CreatedDate
    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    @Column(name = "MODIFIED_DATE_TIME")
    private LocalDateTime modifiedDateTime;

    @Builder
    public Review(String reviewerId, String reviewTargetId, PartyInfo partyInfo, ReviewContents reviewContents) {
        this.reviewerId = reviewerId;
        this.reviewTargetId = reviewTargetId;
        this.partyInfo =partyInfo;
        this.reviewContents = reviewContents;
        this.reviewStatus = ReviewStatus.REGISTERED;
    }

    public void modifyReviewContents(ReviewContents reviewContents) {
        this.reviewContents = reviewContents;
    }
/* 
    public void ReviewScoreAvg(String reviewTargetId){

             
        List<Review> result = reviewRepository.getReviewScoreAvgbyReviewTargetId(reviewTargetId);
        

    } */
}