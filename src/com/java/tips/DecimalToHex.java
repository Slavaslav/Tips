package com.java.tips;

import java.util.HashMap;
import java.util.Map;

public class DecimalToHex {
    public static void main(String[] args) {
        Map<Integer, String> hexMap = new HashMap<>();
        hexMap.put(10, "A");
        hexMap.put(11, "B");
        hexMap.put(12, "C");
        hexMap.put(13, "D");
        hexMap.put(14, "E");
        hexMap.put(15, "F");

        int source = 123456789; // 0x75BCD15
        StringBuilder result = new StringBuilder();

        while (source != 0) {
            int remainder = source % 16;
            source = source / 16;
            result.insert(0, hexMap.containsKey(remainder) ? hexMap.get(remainder) : remainder);
        }
        System.out.println(result.toString());
    }
}
