package com.mungta.review.api.dto;

import com.mungta.review.domain.Review;
import com.mungta.review.domain.ReviewStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ReviewListResponse {

    @Schema(description = "리뷰 리스트")
    private List<ReviewInfoResponse> reviews;

    public static ReviewListResponse of(List<Review> reviewList) {
        List<ReviewInfoResponse> reviewResponseList = reviewList.stream()
                .map(ReviewInfoResponse::of)
                .collect(Collectors.toList());

        return new ReviewListResponse(reviewResponseList);
    }

}
