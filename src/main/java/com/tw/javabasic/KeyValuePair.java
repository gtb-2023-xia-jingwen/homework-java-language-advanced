package com.tw.javabasic;

public class KeyValuePair<TKey, TValue> {   //NOSONAR
    private final TKey key;
    private final TValue value;

    public KeyValuePair(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }

    public TKey getKey() {
        return key;
    }

    public TValue getValue() {
        return value;
    }
}
