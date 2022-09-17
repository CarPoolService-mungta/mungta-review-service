package com.mungta.review.service.exception;

public class NotFoundReviewException extends IllegalArgumentException {

    public NotFoundReviewException(String message) {
        super(message);
    }

}
