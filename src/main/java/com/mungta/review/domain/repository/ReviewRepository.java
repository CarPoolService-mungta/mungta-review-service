package com.mungta.review.domain.repository;

import com.mungta.review.domain.Review;

import feign.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/* import org.springframework.data.repository.query.Param; */

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findById(String id);
  
    List<Review> findByReviewerId(String reviewerId);
    
    List<Review> findByReviewTargetId(String reviewTargetId);  
    @Query(value= "SELECT r.*"
    +" FROM review r "
    +" WHERE r.reviewer_target_id = :reviewTargetId AND r.carpool_role =:carPoolrole" ,nativeQuery= true)
     List<Review> getReviewScoreAvgbyReviewTargetId(@Param("reviewTargetId") String reviewTargetId, @Param("carPoolrole") String carPoolrole);

/*      

    @Query(value= "SELECT avg(r.review_score) as avg"
    +" FROM review r "
    +" WHERE r.reviewer_target_id = :reviewTargetId",nativeQuery= true)
     List<Review> getReviewScoreAvgbyReviewTargetId(@Param("reviewTargetId") String reviewTargetId); */
}
