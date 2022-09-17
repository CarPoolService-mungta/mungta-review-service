package com.mungta.review.api.dto;

import com.mungta.review.domain.Review;
import com.mungta.review.domain.ReviewScore;
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
public class CarPoolRuleListResponse {
    @Schema(description = "리뷰 리스트")
    private List<ReviewInfoResponse> reviews;

    public static CarPoolRuleListResponse of(List<Review> reviewList) {
        List<ReviewInfoResponse> reviewResponseList = reviewList.stream()
                .map(ReviewInfoResponse::of)
                .collect(Collectors.toList());

        return new CarPoolRuleListResponse(reviewResponseList);
    }

    @EqualsAndHashCode
    @Getter
    @Builder
    public static class ReviewInfoResponse {

        @Schema(description = "리뷰 ID")
        private long id;

        @Schema(description = "파티 ID")
        private long partyId;

        @Schema(description = "리뷰 점수")
        private ReviewScore reviewScore;

        @Schema(description = "리뷰 내용")
        private String comment;

        @Schema(description = "리뷰 상태")
        private ReviewStatus reviewStatus;

        @Schema(description = "리뷰등록 시간")
        private String createdDateTime;

        @Schema(description = "리뷰수정 시간")
        private String modifiedDateTime;

        public static ReviewInfoResponse of(Review review) {
            return ReviewInfoResponse.builder()
                    .id(review.getId())
                    .partyId(review.getPartyInfo().getPartyId())
                    .reviewScore(review.getReviewContents().getReviewScore())
                    .comment(review.getReviewContents().getComment())
                    .reviewStatus(review.getReviewStatus())
                    .createdDateTime(
                        review.getCreatedDateTime()
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    )
                    .modifiedDateTime(
                        review.getModifiedDateTime()
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    )
                    .build();
        }

    }

}
