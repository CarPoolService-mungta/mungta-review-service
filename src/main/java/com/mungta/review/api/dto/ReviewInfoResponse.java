package com.mungta.review.api.dto;

import com.mungta.review.domain.Review;
import com.mungta.review.domain.ReviewStatus;
import com.mungta.review.domain.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@EqualsAndHashCode
@Getter
@Builder
public class ReviewInfoResponse {
    @Schema(description = "리뷰 ID")
    private long id;

    @Schema(description = "파티 ID")
    private long partyId;

    @Schema(description = "리뷰 점수")
    private long reviewScore;

    @Schema(description = "리뷰 내용")
    private String comment;

    @Schema(description = "리뷰 타겟")
    private String reviewTargetId;

    @Schema(description = "리뷰 타겟 역할")
    private Role targetRole;

    @Schema(description = "리뷰 상태")
    private ReviewStatus reviewStatus;

    @Schema(description = "리뷰등록 시간")
    private String createdDateTime;

    @Schema(description = "리뷰수정 시간")
    private String modifiedDateTime;

    public static ReviewInfoResponse of(Review review) {

        return ReviewInfoResponse.builder()
                .id(review.getId())
                .partyId(review.getPartyId())
                .reviewScore(review.getReviewContents().getReviewScore())
                .comment(review.getReviewContents().getComment())
                .reviewTargetId(review.getReviewTargetId())
                .targetRole(review.getTargetRole())
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
