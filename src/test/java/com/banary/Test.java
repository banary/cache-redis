package com.banary;

import com.google.common.hash.Hashing;

public class Test {

    public static void main(String[] args) {
        Hashing.murmur3_32().hashLong(10L);

    }
}
