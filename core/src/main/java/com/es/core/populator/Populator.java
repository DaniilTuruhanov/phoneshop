package com.es.core.populator;

public interface Populator<S, T> {
    void populate(S s, T t);
}
