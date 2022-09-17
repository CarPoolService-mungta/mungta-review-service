package com.mungta.review.service;

import com.mungta.review.api.dto.*;
import com.mungta.review.domain.Review;
import com.mungta.review.domain.ReviewContents;
import com.mungta.review.domain.repository.ReviewRepository;
import com.mungta.review.domain.PartyInfo;
import com.mungta.review.service.exception.NotFoundReviewException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public long registeredReview(final ReviewRequest request) {
        PartyInfoRequest partyInfo = request.getPartyInfo();

        return reviewRepository.save(
                Review.builder()
                        .reviewerId(request.getReviewerId())
                        .reviewTargetId(request.getReviewTargetId())
                        .partyInfo(
                                PartyInfo.builder()
                                        .partyId(partyInfo.getPartyId())
                                        .userId(partyInfo.getUserId())
                                        .userName(partyInfo.getUsername())
                                        .profileImage(partyInfo.getProfileImage())
                                        .department(partyInfo.getDepartment())
                                        .carPoolRole(partyInfo.getCarPoolRole())
                                        .build()
                        )
                        .reviewContents(
                                new ReviewContents(request.getReviewContents().getReviewScore(),
                                        request.getReviewContents().getComment())
                        )
                        .build()
        ).getId();
    }

    private Review getReviewById(long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundReviewException("등록되지 않은 리뷰글입니다."));
    }

    public ReviewListResponse getReviewList(final String reviwerId) {
        return ReviewListResponse.of(reviewRepository.findByReviewerId(reviwerId));
    }

    
    public CarPoolRuleListResponse getDriverReviewList(final String reviewTargetId ) {
        return CarPoolRuleListResponse.of(reviewRepository.findByReviewTargetId(reviewTargetId));
    }

    @Transactional
    public ReviewResponse modifyReviewContents(final long id, final ReviewContentsRequest request) {
        Review review = getReviewById(id);

        review.modifyReviewContents(new ReviewContents(request.getReviewScore(), request.getComment()));
        return ReviewResponse.of(review);
    }


    @Transactional
    public void deleteReview(final long id) {
        reviewRepository.deleteById(id);
    }

}
