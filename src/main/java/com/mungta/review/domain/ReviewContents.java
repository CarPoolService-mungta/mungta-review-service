package com.mungta.review.domain;

import lombok.*;
import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReviewContents {

    @Column(name = "REVIEW_SCORE", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ReviewScore reviewScore;
   
    @Column(name = "COMMENT", nullable = false)
    private String comment;


}
