package com.mungta.review.domain.repository;

import com.mungta.review.domain.Review;

import feign.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findById(String id);
  
    List<Review> findByReviewerId(String reviewerId);
    
    List<Review> findByReviewTargetId(String reviewTargetId);  
    @Query(value= "SELECT r.*"
    +" FROM review r "
    +" WHERE r.review_target_id = :reviewTargetId AND r.carpool_role =:carPoolrole" ,nativeQuery= true)
    List<Review> findByReviewTargetIdAndCarPoolRole(@Param("reviewTargetId") String reviewTargetId, @Param("carPoolrole") String carPoolrole);

    //review 평균값
    @Modifying
    @Query(value = "UPDATE review"
    +" SET review_score_avg = (SELECT AVG(rr.review_score) FROM review rr WHERE rr.review_target_id = :reviewTargetId) "
    +" WHERE review_target_id = :reviewTargetId", nativeQuery = true)
    void getReviewScoreAvg(@Param("reviewTargetId") String reviewTargetId);

}
