package com.mungta.review.service;

import com.mungta.review.api.dto.*;
import com.mungta.review.common.ApiException;
import com.mungta.review.common.ApiStatus;
import com.mungta.review.domain.Review;
import com.mungta.review.domain.ReviewContents;
import com.mungta.review.domain.Role;
import com.mungta.review.domain.repository.ReviewRepository;
import com.mungta.review.domain.repository.ReviewRepositorySupport;
import com.mungta.review.service.exception.NotFoundReviewException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewRepositorySupport reviewRepositorySupport;

    @Transactional
    public long registeredReview(final ReviewRequest request) {

        return reviewRepository.save(
                Review.of(request.getReviewerId(),
                        request.getReviewTargetId(),
                        request.getPartyId(),
                        request.getReviewContents().convertReviewContents(),
                        request.getReviewerRole(),
                        request.getTargetRole()
                )
        ).getId();
        
    }

    private Review getReviewById(long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ApiException(ApiStatus.NOT_FOUND_REVIEW));
    }

    public ReviewListResponse getReviewList(final String reviewerId) {
        return ReviewListResponse.of(reviewRepository.findAllByReviewerId(reviewerId));
    }
    
    public ReceivedReviewListResponse getReviewListWithRole(String reviewTargetId, Role role ) {
        return ReceivedReviewListResponse.of(reviewRepository.findAllByReviewTargetIdAndTargetRole(reviewTargetId, role));
    }

    public List<ReviewSummaryResponse> getReviewSummary(List<String> reviewerId) {


        return ReviewSummaryResponse.mergeList(reviewRepositorySupport.getScoreAvg(reviewerId), reviewRepositorySupport.getRecentComment(reviewerId));
    }
 
    @Transactional
    public ReviewResponse modifyReviewContents(final long id, final ReviewContentsRequest request) {
        Review review = getReviewById(id);

        review.modifyReviewContents(request.convertReviewContents());
        return ReviewResponse.of(review);
    }


    @Transactional
    public void deleteReview(final long id) {
        reviewRepository.deleteById(id);
    }

}
