package Task2;

import java.util.Arrays;

class Task2{
    public static void main(String[] args) {
        System.out.println(exercise1.repeat("mice", 5));
        System.out.println(exercise2.differenceMaxMin(10, 4, 1, 4, -10, -50, 32, 21));
        System.out.println(exercise3.isAvgWhole(1, 3));
        System.out.println(exercise4.cumulativeSum(1, 2, 3));
        System.out.println(exercise5.getDecimalPlaces("43.20"));
        System.out.println(exercise6.Fibonacci(3));
        System.out.println(exercise7.isValid("59001"));
        System.out.println(exercise8.isStrangePair("ratio", "orator"));
        System.out.println(exercise9.isPrefix("automation", "auto-"));
        System.out.println(exercise10.boxSeq(2));
    }
}

class exercise1 {
    public static String repeat(String s, int a) {
        String k="";
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < a; j++) k += s.charAt(i);
        }
    return k;
    }
}

class exercise2 {
    public static int differenceMaxMin(int  ... a){
        int max=a[1];
        int min=a[1];
        for (int k:a){
            if (k>max) max=k;
            if (k<min) min=k;
        }
    return (max-min);}
}

class exercise3 {
    public static boolean isAvgWhole(int ... a){
        int k=0;
        int sum = 0;
        for (int b:a){
            sum+=b;
            k++;
        }
    return (sum%k)==0 ;}
}

class exercise4 {
    public static String cumulativeSum(int ... a){
        int [] res = new int[a.length];
        int i=0;
        int s=0;
        for (int b:a){
            s+=b;
            res[i++]=s;
        }
    return Arrays.toString(res);}
}

class exercise5 {
    public static String getDecimalPlaces(String a){
        int b = a.indexOf(".");
        return b==-1 ? "0" : String.valueOf(a.substring(b+1).length());
    }
}

class exercise6 {
    public static int Fibonacci(int a){
       int a1=1;
       int a2=1;
       int a3;
       while(a>0){
           a3=a2+a1;
           a1=a2;
           a2=a3;
           a--;
       }
       return a1;
    }
}

class exercise7 {
    public static boolean isValid(String a){
        boolean res = true;
        first: {
            if (a.length()!=5){
                res = false;
                break first;
            }
            for (char c : a.toCharArray()){
                if (!Character.isDigit(c)){
                    res= false;
                    break first;
                }
            }
        }
    return res;}
}

class exercise8{
    public static boolean isStrangePair (String a, String b){
        return (a.charAt(0)==b.charAt(b.length()-1))&(a.charAt(a.length()-1)==b.charAt(0));
    }
}

class exercise9{
    public static boolean isPrefix(String a, String b){
        return a.startsWith(b.substring(0,b.length()-1));
    }

    public static boolean isSuffix(String a, String b){
        return a.endsWith(b.substring(1));
    }
}

class exercise10{
    public static int boxSeq(int a){
        int sum=0;
        for (int i=1;i<=a;i++) {
            switch (i % 2) {
                case 1:
                    sum += 3;
                    break;
                case 0:
                    sum -= 1;
                    break;
            }
        }
            return sum;}
    }
