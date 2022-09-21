package com.mungta.review.domain.repository;

import com.mungta.review.api.dto.ReviewSummaryResponse;
import com.mungta.review.domain.Review;

import com.mungta.review.domain.Role;
import feign.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findById(String id);
  
    List<Review> findAllByReviewerId(String reviewerId);
    
    List<Review> findByReviewTargetId(String reviewTargetId);

    Optional<Review> findByReviewerIdAndReviewTargetIdAndPartyId(String reviewerId, String reviewTargetId, Long partyId);

    List<Review> findAllByReviewTargetIdAndTargetRole(String reviewTargetId, Role role);
//    @Query(value= "SELECT r.*"
//    +" FROM review r "
//    +" WHERE r.review_target_id = :reviewTargetId AND r.carpool_role =:carPoolrole" ,nativeQuery= true)
//    List<Review> findByReviewTargetIdAndCarPoolRole(@Param("reviewTargetId") String reviewTargetId, @Param("carPoolrole") String carPoolrole);

    //review 평균값
    @Modifying
    @Query(value = "UPDATE review"
    +" SET review_score_avg = (SELECT AVG(rr.review_score) FROM review rr WHERE rr.review_target_id = :reviewTargetId) "
    +" WHERE review_target_id = :reviewTargetId", nativeQuery = true)
    void getReviewScoreAvg(@Param("reviewTargetId") String reviewTargetId);


    @Query(value= "SELECT AVG_R.REVIEW_TARGET_ID AS reviewTargetId," +
            "       AVG_R.AVG_SCORE AS scoreAvg," +
            "       COM_R.COMMENT AS comment" +
            "       FROM (select AVG_R.REVIEW_TARGET_ID," +
            "             AVG(AVG_R.REVIEW_SCORE) AS AVG_SCORE" +
            "      FROM review AVG_R" +
            "      GROUP BY REVIEW_TARGET_ID) AVG_R" +
            "     INNER JOIN" +
            "    (SELECT REVIEW_TARGET_ID," +
            "            COMMENT" +
            "     FROM review" +
            "     WHERE id IN (SELECT MAX(id)" +
            "                  FROM review" +
            "                  GROUP BY REVIEW_TARGET_ID)) COM_R" +
            "ON AVG_R.REVIEW_TARGET_ID = COM_R.REVIEW_TARGET_ID" +
            "WHERE AVG_R.REVIEW_TARGET_ID IN (:userIds)" ,nativeQuery= true)
    List<Review> getReviewSummary(@Param("userIds") List<String> userIds);

}
