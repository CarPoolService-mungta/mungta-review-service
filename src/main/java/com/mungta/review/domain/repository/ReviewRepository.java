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

    @Query(value= "SELECT avg(r.reviewScore)"
    +"FROM Review r"
    +"WHERE r.reviewTargetId = :reviewTargetId",nativeQuery= true)
    /* String findReviewScoreavg(@Param("reviewTargetId") String reviewTargetId); */
     List<Review> getReviewScoreAvebyReviewTargetId(@Param("reviewTargetId") String reviewTargetId);
 
}
