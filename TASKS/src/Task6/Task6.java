package Task6;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

class Task6 {
    public static void main(String[] args) {
        //System.out.println(exercise1.bell(6));
        //System.out.println(exercise2.translateWord("waffles"));
        //System.out.println(exercise2.translateSentence("I like to eat honey waffles."));
        //System.out.println(exercise3.validColor("rgba(0,0,0,0.23)"));
        //System.out.println(exercise4.stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        //System.out.println(exercise5.getHashTags("Visualizing"));
        //System.out.println(exercise6.ulam(206));
        //System.out.println(exercise7.longestNonrepeatingSubstring("аиваприора"));
        //System.out.println(exercise8.convertToRoman(1426));
        //System.out.println(exercise9.formula("18 / 17 = 2"));
        //System.out.println(exercise10.palindromedescendant("123312"));
    }
}

class exercise1 {
    public static int bell(int length) {
        if (length == 1) return 1;
        else if (length == 2) return 2;
        else {
            int[][] mas = new int[length][length];
            mas[1][0] = mas[0][0] = 1;
            mas[1][1] = 2;
            for (int j = 2; j < length; j++) {
                mas[j][0] = mas[j - 1][j - 1];
                for (int i = 1; i <= j; i++) mas[j][i] = mas[j][i - 1] + mas[j - 1][i - 1];
            }
            return mas[length - 1][length - 1];
        }
    }
}

class exercise2 {
    static String translateWord(String word) {
        StringBuilder w = new StringBuilder(word);
        if (String.valueOf(w.charAt(0)).toLowerCase().matches("[aeiou]")) w.append("y");
        while (!String.valueOf(w.charAt(0)).toLowerCase().matches("[aeiou]")) {
            w.append(Character.toLowerCase(w.charAt(0))).deleteCharAt(0);
            if (Character.isUpperCase(word.charAt(0))) w.setCharAt(0, Character.toUpperCase(w.charAt(0)));
        }
        w.append("ay");
        return w.toString();
    }

    static String translateSentence(String text) {
        StringBuilder t = new StringBuilder();
        for (String s : text.split(" ")) {
            if (s.substring(s.length() - 1).matches("[ ,.!?]"))
                t.append(translateWord(s.substring(0, s.length() - 1))).append(s.substring(s.length() - 1)).append(" ");
            else t.append(translateWord(s)).append(" ");
        }
        return t.toString();
    }
}

class exercise3 {
    static boolean validColor(String rgb) {
        if (rgb.matches("rgb\\((([01]?\\d?\\d|2[0-5]{2}),){2}([01]?\\d\\d|2[0-5]{2})\\)")) return true;
        else return rgb.matches("rgba\\((([01]?\\d\\d|2[0-5]{2}),){3}(0|0.[0-9]+|1)\\)");
    }
}

class exercise4 {
    public static String stripUrlParams(String... strings) {
        String str1 = strings[0].substring(0, strings[0].indexOf("?") + 1);
        String str12 = strings[0].substring(strings[0].indexOf("?") + 1);
        String[] split = str12.split("&");
        String list = "";
        if (strings.length == 2) {
            list += strings[1];
        }
        String result = "";
        for (int i = split.length - 1; i >= 0; i--) {
            if (!list.contains(String.valueOf(split[i].charAt(0)))) {
                list += split[i].charAt(0);
                result += split[i] + "&";
            }
        }
        result = str1 + result.substring(0, result.length() - 1);
        return result;
    }
}

class exercise5 {
    static String getHashTags(String text){
        ArrayList<String> word = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        for (String s : text.split(" ")) word.add(s.replaceAll("\\W",""));
        word.sort((o1, o2) -> o2.length() - o1.length());
        for (int i = 0; i<word.size() & i<3;i++) res.append("#").append(word.get(i)).append(" ");
        return res.toString();
    }
}

class exercise6 {
    public static int ulam(int n) {
        Vector<Integer> arr = new Vector<Integer>();
        arr.add(1);
        arr.add(2);
        for (int i = 3; i < Integer.MAX_VALUE; i++) {
            int count = 0;
            for (int j = 0; j < arr.size() - 1; j++) {
                for (int k = j + 1; k < arr.size(); k++) {
                    if (arr.get(j) + arr.get(k) == i) {
                        count++;
                    }
                    if (count > 1)
                        break;
                }
                if (count > 1)
                    break;
            }
            if (count == 1) {
                arr.add(i);
            }
            if (arr.size() >= n) break;
        }
        return arr.lastElement();
    }
}

class exercise7 {
    public static String longestNonrepeatingSubstring(String str) {
        String firstW = "";
        String lastW = "";
        for (int i = 0; i < str.length(); i++) {
            if (!firstW.contains(String.valueOf(str.charAt(i)))) {
                firstW += str.charAt(i);
                if (firstW.length() > lastW.length()) lastW = firstW;
            } else {
                str = str.substring(1);
                firstW = "";
                i = -1;
            }
        }
        return lastW;
    }
}

class exercise8 {
    public static String convertToRoman(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;
        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }
}

class exercise9 {
    static boolean formula (String formula){
        formula+=" = " + formula;
        System.out.println(formula);
        String [] arr = formula.split(" ");
        int n=0,n1=0,i=0;
        while(i<7) {
            if (arr[i+1].equals("+")) n1 = Integer.parseInt(arr[i]) + Integer.parseInt(arr[i+2]);
            else if (arr[i+1].equals("-")) n1 = Integer.parseInt(arr[i]) - Integer.parseInt(arr[i+2]);
            else if (arr[i+1].equals("*")) n1 = Integer.parseInt(arr[i]) * Integer.parseInt(arr[i+2]);
            else if (arr[i+1].equals("/") & !arr[i+2].equals("0")) n1 = Integer.parseInt(arr[i]) / Integer.parseInt(arr[i+2]);
            n = n1;
            i+=6;
        }
        return Integer.parseInt(arr[4])==n & Integer.parseInt(arr[4])==n1;
    }
}

class exercise10 {
    public static boolean palindromedescendant(String c) {
        boolean res = false;
        while (!res) {
            int cislo = parseInt(c);
            c="";
            while (cislo>0){
                c+=String.valueOf(cislo%10+(cislo%100/10));
                cislo=cislo/100;
            }
            c=reverseString(c);
            res = c.equals(reverseString(c));
            if ( c.length() % 2 == 1) break;
        }
        return res;
    }

    private static String reverseString(String s) {
        String b = ("");
        for (int i = 0; i < s.length(); i++) {
            b += s.charAt(s.length() - i - 1);
        }
        return b;
    }
}
