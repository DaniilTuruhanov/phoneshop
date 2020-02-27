package com.es.core.populators.interfaces;

public interface Populator<S,T> {
    void populate(S s,T t);
}
