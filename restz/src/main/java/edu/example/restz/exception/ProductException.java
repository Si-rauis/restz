package edu.example.restz.exception;

public enum ProductException {
    NOT_FOUND("Product NOT_FOUND", 404),
    NOT_REGISTERED("Product NOT_Registered", 400),
    NOT_MODIFIED("Product NOT_Modified", 400),
    NOT_REMOVED("Product NOT_Removed", 400),
    NOT_FETCHED("Product NOT_Fetch", 400),
    NOT_IMAGE("Product NOT_IMAGE", 400),
    REGISTER_ERR("NO Authenticated user", 403);

    private ProductTaskException productTaskException;

    ProductException(String message, int code) {
        productTaskException = new ProductTaskException(message, code);
    }

    public ProductTaskException get(){
        return productTaskException;
    }
}
