package Task5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Task5 {
    public static void main(String[] args) {
        System.out.println(exercise1.encrypt("Sunshine"));
        System.out.println(exercise1.decrypt( new int[]{83, 34, -7, 5, -11, 1, 5, -9}));
        System.out.println(exercise2.canMove("Queen", "C4", "E6"));
        System.out.println(exercise3.canComplete("bauul", "beautiful"));
        System.out.println(exercise4.sumDigProd(16, 28));
        System.out.println(exercise5.sameVowelGroup("hoops", "chuff", "bot", "bottom"));
        System.out.println(exercise6.validateCard(new BigInteger("1234567890123452")));
        System.out.println(exercise7.numToEng(126));
        System.out.println(exercise8.getSha256Hash("password123"));
        System.out.println(exercise9.correctTitle("jOn SnoW, kINg IN thE noRth."));
        exercise10.hexLattice(469);


    }
}

class exercise1 {
    public static String encrypt(String s) {
        int[] res = new int[s.length()];
        int cc = res[0] = (int) s.charAt(0);
        int i = 1;
        s = s.substring(1);
        for (char c : s.toCharArray()) {
            res[i] = (int) c - cc;
            i++;
            cc = (int) c;
        }
        return Arrays.toString(res);
    }

    public static String decrypt(int[] a) {
        String res = "";
        int o = 0;
        for (int i : a) {
            if (i == a[0]) {
                res += (char) i;
                o = i;
            } else {
                res += (char) (i + o);
                o = i + o;
            }
        }

        return res;
    }
}

class exercise2 {
    public static boolean canMove(String s, String start, String end) {
        String regex = "ABCDEFGH12345678";
        //if (!(start.matches(regex) & end.matches(regex))) return false;
        int posstarta = start.charAt(0);
        int posstart1 = start.charAt(1);
        int posenda = end.charAt(0);
        int posend1 = end.charAt(1);
        switch (s) {
            case "Pawn": {
                if (posstarta == (int) 'A') {
                    return posenda - posstart1 <= 2;
                } else {
                    return posenda - posstart1 == 1;
                }
            }
            case "Rook": {
                System.out.println(1);
                return posend1 == posstart1 | posenda == posstarta;
            }
            case "Knight": {
                return (Math.abs(posstarta - posenda) == 2 & Math.abs(posstart1 - posend1) == 1) |
                        (Math.abs(posstarta - posenda) == 1 & Math.abs(posstart1 - posend1) == 2);
            }
            case "Bishop": {
                return Math.abs(posstarta - posenda) == Math.abs(posstart1 - posend1);
            }
            case "Queen": {
                System.out.println(4);
                return posend1 == posstart1 | posenda == posstarta |
                        Math.abs(posstarta - posenda) == Math.abs(posstart1 - posend1);
            }
            case "King": {
                return Math.abs(posstarta - posenda) <= 1 & Math.abs(posstart1 - posend1) <= 1;
            }
        }
        return false;
    }
}

class exercise3 {
    public static boolean canComplete(String s, String word) {
        char[] w = s.toCharArray();
        int i = 0;
        for (char c : word.toCharArray()) {
            if (w[i] == c) {
                i++;
            }
        }
        return i == w.length;
    }
}

class exercise4 {
    public static int sumDigProd(int... mas) {
        int sum = 0;
        for (int s : mas) {
            sum += s;
        }
        return rec(sum);
    }

    private static int rec(int a) {
        int sum = 1;
        if (a / 10 == 0) {
            return a;
        } else {
            do {
                sum *= (a % 10);
                a = a / 10;
            } while (a != 0);
        }
        return rec(sum);
    }
}

class exercise5 {
    public static StringBuilder sameVowelGroup(String... s) {
        String regex = "";
        String regexG = "aeiuyo";
        StringBuilder res = new StringBuilder();
        res.append(s[0] + " ");
        for (char reg : s[0].toCharArray()) {
            if (Pattern.compile(String.valueOf(reg)).matcher(regexG).find() &
                    !Pattern.compile(String.valueOf(reg)).matcher(regex).find()) {
                regex += reg;
            }
        }
        for (int i = 1; i < s.length; i++) {
            int j = 0;
            for (char c : regex.toCharArray()) {
                if (Pattern.compile(String.valueOf(c)).matcher(s[i]).find()) j++;
            }
            if (j == regex.length()) res.append(s[i] + " ");
        }
        return res;
    }
}

class exercise6 {
    public static boolean validateCard(BigInteger a) {
        long b= a.longValue();
        int digit = (int) b%10;
        b=b/10; int i=0; long num=0;
        int sum=0;
        while (b!=0){
            num= num*10+ b%10;
            b=b/10;
            i++;
        }
        while (num!=0){
            if(i--%2==1){
                if(num%10*2>=10){
                    sum+=num%10*2%10+num%10*2/10;
                } else {
                    sum+=num%10*2;
                }
            } else {
                sum+=num%10;
            }
            num=num/10;
        }
        return digit==10-sum%10;
    }
}

class exercise7 {
    public static String numToEng(int a) {
        String res = "";
        if (a == 0) {
            res = "zero";
        } else {
            if (a / 100 > 0) {
                switch (a / 100) {
                    case (1):
                        res += "one";
                        break;
                    case (2):
                        res += "two";
                        break;
                    case (3):
                        res += "three";
                        break;
                    case (4):
                        res += "four";
                        break;
                    case (5):
                        res += "five";
                        break;
                    case (6):
                        res += "six";
                        break;
                    case (7):
                        res += "seven";
                        break;
                    case (8):
                        res += "eight";
                        break;
                    case (9):
                        res += "nine";
                        break;
                }
                res += " hundred ";
            }
            if (a / 10 % 10 == 1) {
                switch (a % 100) {
                    case (11):
                        res += "eleven";
                        break;
                    case (12):
                        res += "twelve";
                        break;
                    case (13):
                        res += "thirteen";
                        break;
                    case (14):
                        res += "fourteen";
                        break;
                    case (15):
                        res += "fifteen";
                        break;
                    case (16):
                        res += "sixteen";
                        break;
                    case (17):
                        res += "seventeen";
                        break;
                    case (18):
                        res += "eighteen";
                        break;
                    case (19):
                        res += "nineteen";
                        break;
                }
            } else {
                switch (a / 10 % 10) {
                    case (1):
                        res += "ten ";
                        break;
                    case (2):
                        res += "twenty ";
                        break;
                    case (3):
                        res += "thirty ";
                        break;
                    case (4):
                        res += "forty ";
                        break;
                    case (5):
                        res += "fifty ";
                        break;
                    case (6):
                        res += "sixty ";
                        break;
                    case (7):
                        res += "seventy ";
                        break;
                    case (8):
                        res += "eighty ";
                        break;
                    case (9):
                        res += "ninety ";
                        break;
                }
                switch (a % 10) {
                    case (1):
                        res += "one";
                        break;
                    case (2):
                        res += "two";
                        break;
                    case (3):
                        res += "three";
                        break;
                    case (4):
                        res += "four";
                        break;
                    case (5):
                        res += "five";
                        break;
                    case (6):
                        res += "six";
                        break;
                    case (7):
                        res += "seven";
                        break;
                    case (8):
                        res += "eight";
                        break;
                    case (9):
                        res += "nine";
                        break;
                }
            }
        }
        return res;
    }
}

class exercise8 {
    public static String getSha256Hash(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

class exercise9 {
    static String correctTitle(String s) {
        String result = "";
        for (String c : s.split(" ")) {
            if (!c.toLowerCase().equals("in") & !c.toLowerCase().equals("and") & !c.toLowerCase().equals("of") & !c.toLowerCase().equals("the"))
                result += Character.toUpperCase(c.charAt(0)) + c.substring(1).toLowerCase() + " ";
            else
                result+=c.toLowerCase() + " ";
        }
        return result;
    }
}

class exercise10
{
    public static void hexLattice(int res) {
        boolean state = false;
        int i = 1;
        int k = 1;
        while (i < res) {
            i += (6 * k++);
        }
        int arri=k*2-1;
        String ress[] = new String[arri];
        String strres = "";
        int kk = k;
        if (res == i) {
            for (int j = 1; j <= k; j++) {
                for (int m = 1; m <=k - j; m++) strres += " ";
                String strr = strres;
                for (int m = 1; m <= kk; m++) strres += " o";
                strres += strr + " ";
                kk++;
                ress[j-1]=strres;
                ress[arri-j]=strres;
                strres = "";
            }
            for (String s:ress){
                System.out.println(s);
            }
        } else {
            System.out.print("Неправильное значение. Ближайшее корректное число равно = " + i);
        }
    }
}
