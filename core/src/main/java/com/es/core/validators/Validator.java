package com.es.core.validators;

public interface Validator<T> {
    void validate(T validateObject, ErrorMap errorMap);
}
