package com.Data_Structure.BloomFilter;

public class Test {
    @SuppressWarnings("all")
    public static void main(String[] args) {

        int a = 0; // 32bit

        int[] filter = new int[10]; // 32bit * 10

        int idx = 178; // 待查找的bit位

        int numIndex = idx / 32;
        int bitIndex = idx % 32;

        int s = ((filter[numIndex] >> bitIndex) & 1); // 查
        filter[numIndex] = filter[numIndex] | (1 << bitIndex); // 改1
        filter[numIndex] = filter[numIndex] & (~(1 << bitIndex)); // 改0

    }
}
