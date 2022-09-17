package com.mungta.review.api.dto;

import com.mungta.review.domain.ReviewContents;
import com.mungta.review.domain.ReviewScore;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@Builder
public class ReviewContentsResponse {

    private ReviewScore reviewScore;

    private String comment;

    public static ReviewContentsResponse of(ReviewContents reviewContents) {
        return ReviewContentsResponse.builder()
                .reviewScore(reviewContents.getReviewScore())
                .comment(reviewContents.getComment())
                .build();
    }

}
