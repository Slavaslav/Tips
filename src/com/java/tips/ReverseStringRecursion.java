package com.java.tips;

public class ReverseStringRecursion {
    public static void main(String[] args) {
        System.out.println(reverse("qwerty"));
    }

    /* public static String reverse(String prefix, String inputString) {
         if (prefix.length() != inputString.length()) {
             return reverse(prefix + inputString.charAt(inputString.length() - prefix.length() - 1), inputString);
         }
         return prefix;
     }*/
    // or
    public static String reverse(String inputString) {
        if (inputString.length() == 0) {
            return "";
        }
        return inputString.substring(inputString.length() - 1) + reverse(inputString.substring(0, inputString.length() - 1));
    }
}
