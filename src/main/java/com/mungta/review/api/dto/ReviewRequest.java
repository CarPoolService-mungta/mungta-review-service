package com.mungta.review.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

    private String reviewerId;

    private String reviewTargetId;

    private PartyInfoRequest partyInfo;

    private ReviewContentsRequest reviewContents;

}
