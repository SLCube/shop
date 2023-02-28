package com.slcube.shop.business.review.controller;

import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.review.dto.*;
import com.slcube.shop.business.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Long> saveReview(@RequestBody @Valid ReviewSaveRequestDto requestDto, @AuthenticationPrincipal MemberSessionDto memberSessionDto) {
        Long reviewId = reviewService.saveReview(requestDto, memberSessionDto);
        return new ResponseEntity<>(reviewId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ReviewListResponseDto>> findAllReviews(Long itemId, Pageable pageable) {
        Page<ReviewListResponseDto> reviews = reviewService.findAllReviews(itemId, pageable);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Long> deleteReview(@PathVariable Long reviewId) {
        Long deletedReviewId = reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(deletedReviewId, HttpStatus.OK);
    }

    @PostMapping("/reported")
    public ResponseEntity<Long> reportReview(ReportedReviewSaveRequestDto requestDto) {
        Long reportedReviewId = reviewService.reportReview(requestDto);
        return new ResponseEntity<>(reportedReviewId, HttpStatus.CREATED);
    }

    @GetMapping("/reported")
    public ResponseEntity<Page<ReportedReviewListResponseDto>> findAllReportedReviews(Pageable pageable) {
        Page<ReportedReviewListResponseDto> reportedReviews = reviewService.findAllReportedReviews(pageable);
        return new ResponseEntity<>(reportedReviews, HttpStatus.OK);
    }

    @GetMapping("/reported/{reportedReviewId}")
    public ResponseEntity<ReportedReviewResponseDto> findReportedReview(@PathVariable Long reportedReviewId) {
        ReportedReviewResponseDto reportedReview = reviewService.findReportedReview(reportedReviewId);
        return new ResponseEntity<>(reportedReview, HttpStatus.OK);
    }
}
