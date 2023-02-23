package com.slcube.shop.business.review.service;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.item.repository.ItemRepository;
import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.repository.MemberRepository;
import com.slcube.shop.business.review.dto.ReportedReviewResponseDto;
import com.slcube.shop.business.review.dto.ReportedReviewSaveRequestDto;
import com.slcube.shop.business.review.dto.ReviewResponseDto;
import com.slcube.shop.business.review.dto.ReviewSaveRequestDto;
import com.slcube.shop.common.exception.CustomException;
import com.slcube.shop.common.security.WithMockMember;
import com.slcube.shop.common.security.authenticationContext.MemberContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@WithMockMember
class ReviewServiceImplTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ItemRepository itemRepository;

    private Long itemId;

    @Autowired
    private MemberRepository memberRepository;
    private Member member;

    @BeforeEach
    void beforeEach() {

        Item item = Item.builder()
                .itemName("test item name")
                .price(10000)
                .stockQuantity(10)
                .build();

        itemId = itemRepository.save(item).getId();

        MemberContext memberContext = (MemberContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        member = memberContext.getMember();
        memberRepository.save(member);
    }

    @Test
    @DisplayName("리뷰 저장")
    void saveReviewTest() {

        // given
        ReviewSaveRequestDto requestDto = createReview();

        // when
        Long reviewId = reviewService.saveReview(requestDto, member);

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
        Long reviewId = reviewService.saveReview(requestDto, member);

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
        Long reviewId = reviewService.saveReview(requestDto, member);

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
        Long reviewId = reviewService.saveReview(requestDto, member);

        ReportedReviewSaveRequestDto reportedReviewSaveRequestDto = new ReportedReviewSaveRequestDto();
        reportedReviewSaveRequestDto.setReviewId(reviewId);
        reportedReviewSaveRequestDto.setReportedReason("test reported review reason");

        // when
        Long reportedReviewId = reviewService.reportReview(reportedReviewSaveRequestDto);

        // then
        ReportedReviewResponseDto responseDto = reviewService.findReportedReview(reportedReviewId);
        assertAll(
                () -> assertThat(responseDto.getReportedReason()).isEqualTo("test reported review reason"),
                () -> assertThat(responseDto.getReviewId()).isEqualTo(reviewId)
        );
    }

    private ReviewSaveRequestDto createReview() {

        ReviewSaveRequestDto requestDto = new ReviewSaveRequestDto();
        requestDto.setItemId(itemId);
        requestDto.setReviewRate(4.5);
        requestDto.setReviewContent("test review content");
        return requestDto;
    }
}