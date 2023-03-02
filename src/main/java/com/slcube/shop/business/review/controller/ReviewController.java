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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> saveReview(@RequestBody @Valid ReviewSaveRequestDto requestDto, @AuthenticationPrincipal MemberSessionDto memberSessionDto) {
        reviewService.saveReview(requestDto, memberSessionDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .queryParam("itemId", requestDto.getItemId())
                .build()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> findReview(@PathVariable Long reviewId) {
        ReviewResponseDto review = reviewService.findReview(reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ReviewListResponseDto>> findAllReviews(Long itemId, Pageable pageable) {
        Page<ReviewListResponseDto> reviews = reviewService.findAllReviews(itemId, pageable);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/recommended/{reviewId}")
    public ResponseEntity<Void> recommendReview(@PathVariable Long reviewId) {
        Long recommendedReviewId = reviewService.recommendReview(reviewId);

        URI location = ServletUriComponentsBuilder.fromCurrentServletMapping()
                .path("/api/reviews/{reviewId}")
                .buildAndExpand(recommendedReviewId)
                .toUri();

        return ResponseEntity.ok().location(location).build();
    }

    @PostMapping("/reported")
    public ResponseEntity<Void> reportReview(@RequestBody @Valid ReportedReviewSaveRequestDto requestDto) {
        reviewService.reportReview(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
