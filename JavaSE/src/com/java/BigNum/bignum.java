package com.java.BigNum;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * bignum
 */
public class bignum {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        BigInteger i = new BigInteger("99999999999");
        BigDecimal j = new BigDecimal("10.0");

        // i.add(j); // 大数之间不能混合使用

        BigDecimal k = new BigDecimal("3");
        BigDecimal[] x = j.divideAndRemainder(k);
        System.out.println(Arrays.toString(x));

    }
}