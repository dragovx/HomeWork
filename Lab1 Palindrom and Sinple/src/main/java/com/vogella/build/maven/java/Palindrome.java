package com.vogella.build.maven.java;

public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (isPalindrome(s) == true){
                System.out.println(s + " является палиндромом");
            } else {
                System.out.println(s + " не является палиндромом");
            }
        }
    }
    /** Переворот строки */
    public static String reverseString(String s) {
        String b = ("");
        for (int i = 0; i < s.length(); i++) {
             b += s.charAt(s.length()-i-1);
        }
    return b;
    }
    /** Проверяет равенсто строки с перевернутой строкой */
    public static boolean isPalindrome(String s){
        if (s.equals(reverseString(s))){
            return true;
        } else {
            return false;
        }
    }
}
