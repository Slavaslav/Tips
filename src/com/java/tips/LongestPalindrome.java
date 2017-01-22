package com.java.tips;

public final class LongestPalindrome {
    public static void main(String[] args) {
        String s = "12145445499"; // 454454
        String longestPalindrome = "";

        for (int i = 0; i < s.length(); i++) {
            int offset = 0;
            while (true) {
                if (i + offset >= s.length() || s.charAt(i) != s.charAt(i + offset)) {
                    break;
                }
                StringBuilder palindrome = new StringBuilder(s.substring(i, i + offset + 1));
                int indexLeft = i;
                int indexRight = i + offset;
                while (true) {
                    indexLeft--;
                    indexRight++;
                    if (indexLeft < 0 || indexRight >= s.length() || s.charAt(indexLeft) != s.charAt(indexRight)) {
                        if (longestPalindrome.length() < palindrome.length()) {
                            longestPalindrome = palindrome.toString();
                        }
                        break;
                    }
                    palindrome.insert(0, s.charAt(indexLeft)).append(s.charAt(indexRight));
                }
                offset++;
            }
        }
        System.out.println(longestPalindrome);
    }
}