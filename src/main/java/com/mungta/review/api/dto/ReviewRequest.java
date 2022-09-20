package com.mungta.review.api.dto;

import com.mungta.review.domain.Role;
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

    private Long partyId;

    private ReviewContentsRequest reviewContents;

    private Role reviewerRole;

    private Role targetRole;

}
