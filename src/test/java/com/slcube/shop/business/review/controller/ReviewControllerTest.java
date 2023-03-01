package com.slcube.shop.business.review.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.review.dto.*;
import com.slcube.shop.business.review.service.ReviewService;
import com.slcube.shop.common.security.TestSecurityConfig;
import com.slcube.shop.common.security.WithMockMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = ReviewController.class)
@ImportAutoConfiguration(TestSecurityConfig.class)
@WithMockMember
class ReviewControllerTest {

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("리뷰 저장")
    void saveReviewTest() throws Exception {
        Long reviewId = 1L;
        given(reviewService.saveReview(any(ReviewSaveRequestDto.class), any(MemberSessionDto.class)))
                .willReturn(reviewId);

        ReviewSaveRequestDto requestDto = new ReviewSaveRequestDto();

        ReflectionTestUtils.setField(requestDto, "itemId", 1L);
        ReflectionTestUtils.setField(requestDto, "reviewRate", 5.0);
        ReflectionTestUtils.setField(requestDto, "reviewContent", "좋아요");

        mockMvc.perform(post("/api/reviews")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("http://localhost/api/reviews?itemId=1"))
                .andDo(print());
    }

    @Test
    @DisplayName("리뷰 리스트 조회")
    void findAllReviewsTest() throws Exception {
        List<ReviewListResponseDto> reviewList = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            ReviewListResponseDto review = new ReviewListResponseDto();
            ReflectionTestUtils.setField(review, "reviewId", (long) i);
            ReflectionTestUtils.setField(review, "reviewer", "test reviewer " + i);
            ReflectionTestUtils.setField(review, "reviewRate", 1.5 * i);
            ReflectionTestUtils.setField(review, "reviewContent", "test review content " + i);
            ReflectionTestUtils.setField(review, "recommendedCount", 2 * i);
            reviewList.add(review);
        }

        Pageable pageable = PageRequest.of(0, 10);

        Page<ReviewListResponseDto> reviews = new PageImpl<>(reviewList, pageable, reviewList.size());

        given(reviewService.findAllReviews(anyLong(), any(Pageable.class)))
                .willReturn(reviews);

        mockMvc.perform(get("/api/reviews")
                        .queryParam("itemId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(reviews)))
                .andDo(print());
    }

    @Test
    @DisplayName("리뷰 삭제")
    void deleteReviewTest() throws Exception {
        Long deletedReviewId = 1L;

        given(reviewService.deleteReview(anyLong()))
                .willReturn(deletedReviewId);

        mockMvc.perform(delete("/api/reviews/" + deletedReviewId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deletedReviewId)))
                .andDo(print());
    }

    @Test
    @DisplayName("리뷰 추천")
    void recommendReviewTest() throws Exception {
        Long recommendedReviewId = 1L;

        given(reviewService.recommendReview(anyLong()))
                .willReturn(recommendedReviewId);

        mockMvc.perform(patch("/api/reviews/recommended/" + recommendedReviewId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(recommendedReviewId)))
                .andDo(print());
    }

    @Test
    @DisplayName("리뷰 신고")
    void reportReviewTest() throws Exception {

        Long reportedReviewId = 1L;

        given(reviewService.reportReview(any(ReportedReviewSaveRequestDto.class)))
                .willReturn(reportedReviewId);

        ReportedReviewSaveRequestDto requestDto = new ReportedReviewSaveRequestDto();

        ReflectionTestUtils.setField(requestDto, "reviewId", 1L);
        ReflectionTestUtils.setField(requestDto, "reportedReason", "test reported reason");

        mockMvc.perform(post("/api/reviews/reported")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(reportedReviewId)))
                .andDo(print());
    }

    @Test
    @DisplayName("신고된 리뷰 조회")
    void findAllReportedReviewsTest() throws Exception {

        List<ReportedReviewListResponseDto> reportedReviewList = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            ReportedReviewListResponseDto reportedReview = new ReportedReviewListResponseDto();

            ReflectionTestUtils.setField(reportedReview, "reportedReviewId", (long) i);
            ReflectionTestUtils.setField(reportedReview, "reviewId", 1L);
            ReflectionTestUtils.setField(reportedReview, "reportedReason", "test report reason " + i);

            reportedReviewList.add(reportedReview);
        }

        Pageable pageable = PageRequest.of(0, 10);

        Page<ReportedReviewListResponseDto> reportedReviews = new PageImpl<>(reportedReviewList, pageable, reportedReviewList.size());

        given(reviewService.findAllReportedReviews(any(Pageable.class)))
                .willReturn(reportedReviews);

        mockMvc.perform(get("/api/reviews/reported"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(reportedReviews)))
                .andDo(print());
    }

    @Test
    @DisplayName("신고된 리뷰 단건 조회")
    void findReportedReviewTest() throws Exception {
        ReportedReviewResponseDto responseDto = new ReportedReviewResponseDto();

        ReflectionTestUtils.setField(responseDto, "reportedReviewId", 1L);
        ReflectionTestUtils.setField(responseDto, "reviewId", 1L);
        ReflectionTestUtils.setField(responseDto, "reportedReason", "test report reason");

        given(reviewService.findReportedReview(anyLong()))
                .willReturn(responseDto);

        Long reportedReviewId = 1L;

        mockMvc.perform(get("/api/reviews/reported/" + reportedReviewId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)))
                .andDo(print());
    }
}