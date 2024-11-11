package edu.example.restz.exception;

public enum ReviewException {
    NOT_FOUND("Review NOT_FOUND", 404   ),
    PRODUCT_NOT_FOUND("Product NOT_FOUND for Review", 404),
    NOT_REGISTERED("Review NOT_Registered", 400),
    NOT_MODIFIED("Review NOT_Modified", 400),
    NOT_REMOVED("Review NOT_Removed", 400),
    NOT_FETCHED("Review NOT_Fetch", 400),
    REGISTER_ERR("Review NOT Registered user", 403),
    NOT_MATCHED_REVIEWER("Review NOT Matched User", 400);

    private ReviewTaskException reviewTaskException;

    ReviewException(String message, int code) {
        reviewTaskException = new ReviewTaskException(message, code);
    }

    public ReviewTaskException get(){
        return reviewTaskException;
    }
}
