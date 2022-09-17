package com.mungta.review.api.dto;

import com.mungta.review.domain.PartyInfo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@Builder
public class PartyInfoResponse {

    private long partyId;

    private String userId;

    private String userName;

    private String profileImage;

    private String department;

    private String carPoolRole;

    public static PartyInfoResponse of(PartyInfo partyInfo) {
        return PartyInfoResponse.builder()
                .partyId(partyInfo.getPartyId())
                .userId(partyInfo.getUserId())
                .userName(partyInfo.getUserName())
                .profileImage(partyInfo.getProfileImage())
                .department(partyInfo.getDepartment())
                .carPoolRole(partyInfo.getCarPoolRole())
                .build();
    }

}
