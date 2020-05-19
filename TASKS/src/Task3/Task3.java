package Task3;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

class Task3{
    public static void main(String[] args) {
        System.out.println(exercise1.solutions(1, 0, -1));
        System.out.println(exercise2.findZip("all zip files are zipped"));
        System.out.println(exercise3.checkPerfect(6));
        System.out.println(exercise4.flipEndChars("Cat, dog, and mouse."));
        System.out.println(exercise5.isValidHexCode("#CD5C5C"));
        System.out.println();
        System.out.println(exercise7.isKaprekar(3));
        System.out.println(exercise8.longestZero("01100001011000"));
        System.out.println(exercise9.nextPrime(12));
        System.out.println(exercise10.rightTriangle(3, 4, 5));
    }
}

class exercise1 {
    public static int solutions(int a, int b, int c) {
        int sum = b * b - 4 * a * c;
        if (sum < 0) {
            return 0;
        } else if (sum == 0) {
            return 1;
        } else {
            return 2;
        }
    }
}

class exercise2 {
    public static int findZip(String s) {
        return s.indexOf("zip", s.indexOf("zip") + 1);
    }
}

class exercise3 {
    public static boolean checkPerfect(int a) {
        int ch = 0;
        for (int i = 1; i < a; i++) {
            if (a % i == 0) {
                ch += i;
            }
        }
        if (ch == a) {
            return true;
        } else return false;
    }
}

class exercise4 {
    public static String flipEndChars(String s) {
        if (s.length() < 2) {
            return "Incompatible.";
        } else if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return "Two's a pair.";
        } else {
            return s.charAt(s.length() - 1) + s.substring(1, s.length() - 1) + s.charAt(0);
        }
    }
}

class exercise5 {
    public static boolean isValidHexCode(String s) {
        return s.matches("(\\#)[a-fA-F0-9]{6}");
    }
}

class exercise6{
    static boolean same (Integer[] arr1,Integer[] arr2) {
        Set<Integer> set1 = new LinkedHashSet<>(Arrays.asList(arr1));
        Set<Integer> set2 = new LinkedHashSet<>(Arrays.asList(arr2));
        if (set1.size()==set2.size()) return true;
        return false;
    }
}

class exercise7{
    public static boolean isKaprekar(int a){
        int a3=a;
        a=a*a;
        int a2=a;
        int i=0;
        int cl,cr;
        while (a2!=0){
            a2=a2/10;
            i++;
        }
        if (i%2==1){
            cl = (int) (a/(Math.pow(10,i/2+1)));
            cr = (int) (a%(Math.pow(10,i/2+1)));
            System.out.println(cl + " " + cr + "  1");
        } else {
            cl = (int) (a/(Math.pow(10,i/2)));
            cr = (int) (a%(Math.pow(10,i/2)));
            System.out.println(cl + " " + cr + "  0");
        }
    return (a3==cl+cr);}
}

class exercise8{
    public static String longestZero(String s){
        int max=0;
        int maxi=0;
        for (int i=0; i<s.length(); i++){
                if(s.charAt(i)=='0'){
                    maxi++;
                    if (maxi>max) max=maxi;
            } else {
                    maxi=0;
                }
        }
        String res="";
        for (int i=1; i<=max; i++) res+="0";
    return res;}
}

class exercise9{
    public static int nextPrime(int a){
        int i=a;
        while (!IsPrime(i)) i++;
    return i;}
    private static boolean IsPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}


class exercise10{
    public static boolean rightTriangle(int a, int b, int c){
        return triangle(a,b,c) | triangle(b,c,a) | triangle(c,a,b);
    }
    private static boolean triangle(int a,int b, int c){
        return Math.sqrt(a*a+b*b)==Math.sqrt(c*c);
    }
}