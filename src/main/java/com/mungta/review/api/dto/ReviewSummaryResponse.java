package com.mungta.review.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSummaryResponse {

    private String userId;

    private String comment;

    private Double scoreAvg;

    public ReviewSummaryResponse(String userId,Double scoreAvg){
        this.userId = userId;
        this.scoreAvg = scoreAvg;
    }
    public ReviewSummaryResponse(String userId, String comment){
        this.userId = userId;
        this.comment = comment;
    }

    public static List<ReviewSummaryResponse> mergeList(List<ReviewSummaryResponse> avgList, List<ReviewSummaryResponse> commentList){
        return avgList.stream().map(avg->{
            avg.setComment(commentList.stream().filter(com->com.getUserId().equals(avg.getUserId())).findFirst().get().getComment());
            return avg;
        }).collect(Collectors.toList());
    }
}
