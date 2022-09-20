package com.mungta.review.domain.repository;

import com.mungta.review.api.dto.QReviewSummaryResponse;
import com.mungta.review.api.dto.ReviewSummaryResponse;
import com.mungta.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.mungta.review.domain.QReview.review;

@Repository
@RequiredArgsConstructor
public class ReviewRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    private EntityManager entityManager;

//    public List<ReviewSummaryResponse> getReviewSummary(List<String> reviewerId){
//        return queryFactory.select(new QReviewSummaryResponse(review.reviewerId,
//                review.reviewContents.comment,
//                review.reviewContents.reviewScore.avg()))
//                .from()
//    }

    public List<ReviewSummaryResponse> getScoreAvg(List<String> userIds){
        return entityManager.createQuery("select " +
                        "new com.mungta.review.api.dto.ReviewSummaryResponse(r.reviewTargetId, AVG(r.reviewContents.reviewScore))" +
//                        " r.reviewTargetId," +
//                        " null," +
//                        " AVG(r.reviewContents.reviewScore)" +
                "FROM Review r WHERE r.reviewTargetId IN (:userIds) GROUP BY r.reviewTargetId")
                .setParameter("userIds", userIds)
                .getResultList();
    }
    public List<ReviewSummaryResponse> getRecentComment(List<String> userIds){
        return entityManager.createQuery("SELECT " +
                "new com.mungta.review.api.dto.ReviewSummaryResponse(r.reviewTargetId, r.reviewContents.comment)" +
                "FROM Review r WHERE r.id IN (" +
                "SELECT MAX(r2.id)" +
                "             FROM Review r2" +
                "             GROUP BY r2.reviewTargetId)" +
                "AND r.reviewTargetId IN (:userIds)")
                .setParameter("userIds", userIds)
                .getResultList();
    }
}
