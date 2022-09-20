package com.mungta.review.api.dto;

import com.mungta.review.domain.ReviewContents;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewContentsRequest {

    @NotBlank
    private long reviewScore;

    private String comment;

    public ReviewContents convertReviewContents(){
        return new ReviewContents(reviewScore,comment);
    }

}
