package com.es.core.populator.inter;

public interface Populator<S,T> {
    void populate(S s,T t);
}
