package com.mungta.review.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Embeddable

public class PartyInfo {

    @Column(name = "PARTY_ID", nullable = false)
    private Long partyId;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "PROFILE_IMAGE", nullable = false)
    private String profileImage;

    @Column(name = "DEPARTMENT", nullable = false)
    private String department;

    @Column(name = "CARPOOL_ROLE", nullable = false)
    private String carPoolRole;

    @Builder
    public PartyInfo(Long partyId, String userId, String userName, String profileImage, String department, String carPoolRole) {
        this.partyId = partyId;
        this.userId = userId;
        this.userName = userName;
        this.profileImage = profileImage;
        this.department = department;
        this.carPoolRole = carPoolRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartyInfo partyInfo = (PartyInfo) o;
        return Objects.equals(partyId, partyInfo.partyId)
                && Objects.equals(userId, partyInfo.userId)
                && Objects.equals(userName, partyInfo.userName)
                && Objects.equals(profileImage, partyInfo.profileImage)
                && Objects.equals(department, partyInfo.department)
                && Objects.equals(carPoolRole, partyInfo.carPoolRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partyId, userId, userName, profileImage, department, carPoolRole);
    }

}
