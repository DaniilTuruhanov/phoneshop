package com.es.core.validators;

public class ErrorMessage {
    private boolean valid;
    private String message;

    public ErrorMessage(boolean isValid, String message) {
        this.valid = isValid;
        this.message = message;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
