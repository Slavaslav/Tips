package com.java.tips;

import java.util.HashSet;
import java.util.Set;

public class PermutationString {
    public static void main(String[] args) {
        String s = "abab";
        for (String s1 : permutations(s)) {
            System.out.println(s1);
        }
    }

    private static Set<String> permutations(String inputString) {
        Set<String> strings = new HashSet<>();
        permutations("", inputString, strings);
        return strings;
    }

    private static void permutations(String pref, String inputStr, Set<String> strings) {
        if (inputStr.length() > 1) {
            for (int i = 0; i < inputStr.length(); i++) {
                permutations(pref + inputStr.charAt(i), inputStr.substring(0, i) + inputStr.substring(i + 1), strings);
            }
        } else if (inputStr.length() == 1) {
            strings.add(pref + inputStr);
        }
    }
}