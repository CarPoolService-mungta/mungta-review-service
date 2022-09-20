package com.mungta.review.api.dto;

import com.mungta.review.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ReceivedReviewListResponse {
    @Schema(description = "리뷰 리스트")
    private List<ReviewInfoResponse> reviews;

    public static ReceivedReviewListResponse of(List<Review> reviewList) {
        List<ReviewInfoResponse> reviewResponseList = reviewList.stream()
                .map(ReviewInfoResponse::of)
                .collect(Collectors.toList());

        return new ReceivedReviewListResponse(reviewResponseList);
    }


}
