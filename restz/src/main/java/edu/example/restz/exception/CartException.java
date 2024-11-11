package edu.example.restz.exception;

public enum CartException {
    NOT_FOUND_CART("Cart NOT_FOUND", 404),
    NOT_FOUND_CARTITEM("CartITEM NOT_FOUND", 404),
    PRODUCT_NOT_FOUND("Product NOT_FOUND for Review", 404),

    FAIL_ADD("Cart Add Fail", 400),
    FAIL_MODIFIED("Cart Modified Fail", 400),
    FAIL_REMOVED("Cart Remove Fail", 400),
    FAIL_FETCHED("Cart Fetch Fail", 400),

    REGISTER_ERR("Review NOT Registered user", 403),
    NOT_MATCHED_CUSTOMER("Cart NOT Matched User", 400),
    NOT_MATCHED_ItemNO("Cart NOT Matched ItemNo", 400);

    private CartTaskException cartTaskException;

    CartException(String message, int code) {
        cartTaskException = new CartTaskException(message, code);
    }

    public CartTaskException get(){
        return cartTaskException;
    }
}
