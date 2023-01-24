package com.slcube.shop.business.review.service;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.item.repository.ItemRepository;
import com.slcube.shop.business.review.dto.ReportedReviewSaveRequestDto;
import com.slcube.shop.business.review.dto.ReviewResponseDto;
import com.slcube.shop.business.review.dto.ReviewSaveRequestDto;
import com.slcube.shop.common.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceImplTest {

    @Autowired
    private ReviewService reviewService;

    // review를 테스트 해야되는데 item repository가 필요하다 앞으로도 이런일이 많을텐데 mocking이 필요할까..?
    @Autowired
    private ItemRepository itemRepository;

    private Long itemId;

    @BeforeEach
    void beforeEach() {

        Item item = Item.builder()
                .itemName("test item name")
                .price(10000)
                .stockQuantity(10)
                .build();

        itemId = itemRepository.save(item).getId();
    }

    @Test
    @DisplayName("리뷰 저장")
    void saveReviewTest() {

        // given
        ReviewSaveRequestDto requestDto = createReview();

        // when
        Long reviewId = reviewService.saveReview(requestDto);

        // then
        ReviewResponseDto findReview = reviewService.findReview(reviewId);


        assertAll(
                () -> assertThat(findReview.getReviewContent()).isEqualTo("test review content"),
                () -> assertThat(findReview.getReviewRate()).isEqualTo(4.5),
                () -> assertThat(findReview.getRecommendCount()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("리뷰 삭제")
    void deleteReviewTest() {

        // given
        ReviewSaveRequestDto requestDto = createReview();
        Long reviewId = reviewService.saveReview(requestDto);

        // when
        Long deletedReviewId = reviewService.deleteReview(reviewId);

        // then
        assertThrows(CustomException.class,
                () -> reviewService.findReview(deletedReviewId));
    }

    @Test
    @DisplayName("리뷰 추천")
    void recommendReviewTest() {

        // given
        ReviewSaveRequestDto requestDto = createReview();
        Long reviewId = reviewService.saveReview(requestDto);

        // when
        Long recommendReviewId = reviewService.recommendReview(reviewId);

        // then
        ReviewResponseDto findReview = reviewService.findReview(recommendReviewId);

        assertThat(findReview.getRecommendCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("리뷰 신고")
    void reportReviewTest() {

        // given
        ReviewSaveRequestDto requestDto = createReview();
        Long reviewId = reviewService.saveReview(requestDto);

        ReportedReviewSaveRequestDto reportedReviewSaveRequestDto = new ReportedReviewSaveRequestDto();
        reportedReviewSaveRequestDto.setReviewId(reviewId);
        reportedReviewSaveRequestDto.setReportedReason("test reported review reason");

        // when
        Long reportedReviewId = reviewService.reportReview(reportedReviewSaveRequestDto);

    }

    private ReviewSaveRequestDto createReview() {

        ReviewSaveRequestDto requestDto = new ReviewSaveRequestDto();
        requestDto.setItemId(itemId);
        requestDto.setReviewRate(4.5);
        requestDto.setReviewContent("test review content");
        return requestDto;
    }
}