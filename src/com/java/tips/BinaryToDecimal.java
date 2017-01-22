package com.java.tips;

import java.io.UnsupportedEncodingException;

public class BinaryToDecimal {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "11110100001001000000"; // 1 000 000
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(s.length() - i - 1);
            if (c == '1') {
                int factor = 1;
                for (int j = 0; j < i; j++) {
                    factor *= 2;
                }
                result += factor;
            }
        }
        System.out.println(result);
    }
}
