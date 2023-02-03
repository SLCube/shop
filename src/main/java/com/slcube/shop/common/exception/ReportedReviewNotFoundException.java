package com.slcube.shop.common.exception;

import static com.slcube.shop.common.exception.CustomErrorCode.*;

public class ReportedReviewNotFoundException extends CustomException {
    public ReportedReviewNotFoundException() {
        super(REPORTED_REVIEWS_NOT_FOUND);
    }
}
