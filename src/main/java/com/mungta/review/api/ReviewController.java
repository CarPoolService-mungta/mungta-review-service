package com.mungta.review.api;

import com.mungta.review.api.dto.*;

import com.mungta.review.domain.Review;
import com.mungta.review.domain.Role;
import com.mungta.review.domain.repository.ReviewRepositorySupport;
import com.mungta.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "REVIEW", description = "리뷰 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;


    @Operation(summary = "리뷰 등록", description = "리뷰를 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "리뷰 작성 성공")
    })
    @PostMapping
    public ResponseEntity<ReviewResponse> registeredReview(@RequestBody ReviewRequest request) {
        long id = reviewService.registeredReview(request);
        return ResponseEntity.created(URI.create("/api/review/list/" + id)).build();
    }

    @Operation(summary = "리뷰 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 수정 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResponse.class))})
    })
    @PutMapping("/list/{id}")
    public ResponseEntity<ReviewResponse> modifyReview(@Parameter(description = "리뷰 ID") @PathVariable long id,
                                                               @RequestBody ReviewContentsRequest request) {
        ReviewResponse response = reviewService.modifyReviewContents(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "내가 작성한 리뷰")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 내역 조회 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewListResponse.class))})
    })
    @GetMapping("/list")
    public ResponseEntity<ReviewListResponse> getReviewList(@RequestParam String reviewerId) {
        ReviewListResponse response = reviewService.getReviewList(reviewerId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "내가 받은 리뷰")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 내역 조회 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReceivedReviewListResponse.class))})
    })
    @GetMapping("/received-review")
    public ResponseEntity<ReceivedReviewListResponse> getDriverReviewList(@RequestParam String reviewTargetId,@RequestParam Role role) {
        ReceivedReviewListResponse response = reviewService.getReviewListWithRole(reviewTargetId,role);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "리뷰 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "리뷰 삭제 성공")
    })
    @DeleteMapping("/list/{id}")
    public ResponseEntity<ReviewResponse> deleteReview(@Parameter(description = "리뷰 ID") @PathVariable long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "회원 리뷰 summary 리스트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 내역 조회 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReceivedReviewListResponse.class))})
    })
    @GetMapping("/summary")
    public ResponseEntity<List<ReviewSummaryResponse>> getReviewSummary(@RequestParam List<String> userIds) {

        return ResponseEntity.ok(reviewService.getReviewSummary(userIds));
    }

}
