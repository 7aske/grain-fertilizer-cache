package com._7aske.grain.cache;

import java.io.Serializable;
import java.util.Arrays;

public class SimpleCacheKey implements CacheKey, Serializable {
    private final Object[] args;
    public SimpleCacheKey(Object... args) {
        this.args = args;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return Arrays.deepEquals(args, ((SimpleCacheKey) obj).args);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(args);
    }
}
