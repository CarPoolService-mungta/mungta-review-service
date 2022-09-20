package com.mungta.review.api.dto;

import com.mungta.review.domain.Review;
import com.mungta.review.domain.ReviewStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponse {

    private long id;

    private String reviewerId;

    private String reviewTargetId;

    private Long partyId;

    private ReviewContentsResponse reviewContents;

    private ReviewStatus reviewStatus;

    public static ReviewResponse of(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .reviewerId(review.getReviewerId())
                .reviewTargetId(review.getReviewTargetId())
                .partyId(review.getPartyId())
                .reviewContents(
                        ReviewContentsResponse.of(review.getReviewContents())
                )
                .reviewStatus(review.getReviewStatus())
                .build();
    }

}