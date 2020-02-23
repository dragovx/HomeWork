package com.vogella.build.maven.java;

public class Primes {

    public static void main(String[] args) {
        System.out.println("Простые числа:");
        for (int i = 2; i < 100; i++) {
            if (IsPrime(i) == true) {
                System.out.println(i);
            }
        }
    }
    /** Проверка на простое число */
    public static boolean IsPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
