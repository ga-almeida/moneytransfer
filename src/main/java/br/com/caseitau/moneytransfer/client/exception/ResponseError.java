package br.com.caseitau.moneytransfer.client.exception;

public class ResponseError {
    private String errorCode;
    private String message;

    private ResponseError(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String errorCode;
        private String message;

        private Builder() {
        }

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ResponseError build() {
            return new ResponseError(errorCode, message);
        }
    }
}
