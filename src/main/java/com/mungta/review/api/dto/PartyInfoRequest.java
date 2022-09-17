package com.mungta.review.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartyInfoRequest {

    private long partyId;

    @NotBlank
    private String userId;

    @NotBlank
    private String username;

    @NotBlank
    private String profileImage;

    @NotBlank
    private String department;

    @NotBlank
    private String carPoolRole;


}
