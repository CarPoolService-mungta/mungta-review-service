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

    private PartyInfoResponse partyInfo;

    private ReviewContentsResponse reviewContents;

    private ReviewStatus reviewStatus;

    public static ReviewResponse of(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .reviewerId(review.getReviewerId())
                .reviewTargetId(review.getReviewTargetId())
                .partyInfo(
                        PartyInfoResponse.of(review.getPartyInfo())
                )
                .reviewContents(
                        ReviewContentsResponse.of(review.getReviewContents())
                )
                .reviewStatus(review.getReviewStatus())
                .build();
    }

}