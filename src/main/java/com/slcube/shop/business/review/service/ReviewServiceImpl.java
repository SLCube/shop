package com.slcube.shop.business.review.service;

import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.review.domain.ReportedReview;
import com.slcube.shop.business.review.domain.Review;
import com.slcube.shop.business.review.dto.*;
import com.slcube.shop.business.review.repository.ReportedReviewRepository;
import com.slcube.shop.business.review.repository.ReviewRepository;
import com.slcube.shop.business.review.repository.ReviewRepositoryHelper;
import com.slcube.shop.business.review.repository.ReviewValidation;
import com.slcube.shop.common.exception.ReportedReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewRepositoryHelper reviewRepositoryHelper;
    private final ReviewValidation reviewValidation;
    private final ReportedReviewRepository reportedReviewRepository;

    @Override
    public Long saveReview(ReviewSaveRequestDto requestDto, MemberSessionDto memberSessionDto) {
        Long itemId = requestDto.getItemId();
        Item item = reviewValidation.validateCreateReview(itemId);

        Review review = ReviewMapper.toEntity(requestDto, memberSessionDto.getMemberId());
        review.addItem(item);
//        review.addMember(member);
        return reviewRepository.save(review).getId();
    }

    @Override
    public Long deleteReview(Long reviewId) {
        Review review = reviewRepositoryHelper.findByNotDeleted(reviewRepository, reviewId);

        review.deleteReview();

        return review.getId();
    }

    @Override
    public Long recommendReview(Long reviewId) {
        Review review = reviewRepositoryHelper.findByNotDeleted(reviewRepository, reviewId);
        review.recommendReview();
        return review.getId();
    }

    @Override
    public ReviewResponseDto findReview(Long reviewId) {
        Review review = reviewRepositoryHelper.findByNotDeleted(reviewRepository, reviewId);

        return new ReviewResponseDto(review);
    }

    @Override
    public Page<ReviewListResponseDto> findAllReviews(Long itemId, Pageable pageable) {
        List<ReviewListResponseDto> reviews = reviewRepository.findByItemId(itemId, pageable).stream()
                .map(ReviewListResponseDto::new)
                .collect(Collectors.toList());

        return new PageImpl<>(reviews, pageable, reviews.size());
    }

    @Override
    public Long reportReview(ReportedReviewSaveRequestDto requestDto) {
        Review review = reviewRepositoryHelper.findByNotDeleted(reviewRepository, requestDto.getReviewId());

        ReportedReview reportedReview = ReportedReviewMapper.toEntity(requestDto);
        reportedReview.addReview(review);
        return reportedReviewRepository.save(reportedReview).getId();
    }

    @Override
    public ReportedReviewResponseDto findReportedReview(Long reportedReviewId) {
        ReportedReview reportedReview = reportedReviewRepository.findById(reportedReviewId)
                .orElseThrow(() -> new ReportedReviewNotFoundException());

        return new ReportedReviewResponseDto(reportedReview);
    }

    @Override
    public Page<ReportedReviewListResponseDto> findAllReportedReviews(Pageable pageable) {
        List<ReportedReviewListResponseDto> reportedReviews = reportedReviewRepository.findAll(pageable).stream()
                .map(ReportedReviewListResponseDto::new)
                .collect(Collectors.toList());
        return new PageImpl<>(reportedReviews, pageable, reportedReviews.size());
    }
}
