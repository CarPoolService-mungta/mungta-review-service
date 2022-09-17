package com.mungta.review.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import com.mungta.review.domain.ReviewScore;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewContentsRequest {

    @NotBlank
    private ReviewScore reviewScore;

    private String comment;

}
