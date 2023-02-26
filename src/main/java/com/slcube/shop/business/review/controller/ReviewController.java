package com.slcube.shop.business.review.controller;

import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.review.dto.ReviewSaveRequestDto;
import com.slcube.shop.business.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
