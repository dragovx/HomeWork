package Task4;

public class Task4 {
    public static void main(String[] args) {
        exercise1.bessie(10, 7, "hello my name is Bessie and this is my essay");
        System.out.println();
        System.out.println(exercise2.split("((()))(())()()(()())"));
        System.out.println(exercise3.toCamelCase("is_modal_open"));
        System.out.println(exercise3.toSnakeCase("helloEdabit"));
        System.out.println(exercise4.overTime(13.25, 15, 30, 1.5));
        exercise5.BMI("154 pounds", "2 meters");
        System.out.println();
        System.out.println(exercise6.bugger(999));
        System.out.println(exercise7.toStarShorthand("abbccc"));
        System.out.println(exercise8.doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(exercise9.trouble(451999277, 411777299));
        System.out.println(exercise10.countUniqueBooks("AZYWABBCATTTA", 'A'));
    }
}

class exercise1 {
    public static void bessie(int n, int k, String c) {
        int maxs = 0;
        String[] i = c.split(" ");
        if (i.length == n) {
            for (String s : i) {
                if (s.length() + maxs <= k) {
                    System.out.print(s + " ");
                    maxs = maxs + s.length();
                } else {
                    System.out.println("");
                    System.out.print(s + " ");
                    maxs = s.length();
                }
            }
        } else {
            System.out.println("Неверное колличество слов");
        }
    }
}

class exercise2 {
    public static String split(String s) {
        int maxl = 0;
        int maxr = 0;
        StringBuilder res = new StringBuilder();
        res.append("[");
        String r = "";
        for (char c : s.toCharArray()) {
            if (c == '(') {
                r += '(';
                maxl++;
            } else {
                maxr++;
                r += ')';
            }
            if (maxl == maxr) {
                maxr = 0;
                maxl = 0;
                res.append("\"" + r + "\", ");
                r = "";
            }
        }
        res.deleteCharAt(res.length() - 1);
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }
}

class exercise3 {
    static String toCamelCase(String snake) {
        while (snake.contains("_"))
            snake = snake.substring(0, snake.indexOf("_")) +
                    Character.toUpperCase(snake.charAt(snake.indexOf("_") + 1)) +
                    snake.substring(snake.indexOf("_") + 2);
        return snake;
    }
    static String toSnakeCase( String camel){
        String result="";
        for (char c : camel.toCharArray())
            result += Character.isUpperCase(c)? "_" + Character.toLowerCase(c): c;
        return result;
    }
}

class exercise4 {
    public static String overTime(double a, double b, double c, double d) {
        double zp = 0;
        if (b > 17) {
            zp = (17 - a) * c + ((b - 17) * d * c);
        } else zp = (b - a) * c;
        return String.format("%.2f", zp);
    }
}

class exercise5 {
    public static void BMI(String weight,String height) {
        double weightk = Double.parseDouble(weight.split(" ")[0]);
        if (weight.split(" ")[1].equals("pounds")) weightk*=0.45;
        double heightm = Double.parseDouble(height.split(" ")[0]);
        if (height.split(" ")[1].equals("inches")) heightm*=0.0254;
        double BMI=weightk/Math.pow(heightm,2);
        if (BMI<18.5){
            System.out.print(String.format("%.1f",BMI)+" Underweight");
        } else if(BMI>=18.5 & BMI<25){
            System.out.print(String.format("%.1f",BMI)+"  Normal weight");
        } else {
            System.out.print(String.format("%.1f",BMI)+" Overweight");
        }
    }
}

class exercise6 {
    public static int bugger(int a) {
        if (a < 10) return 0;
        int ch = 1;
        while (a != 0) {
            ch *= (a % 10);
            a /= 10;
        }
        return 1 + bugger(ch);
    }
}

class exercise7 {
    public static String toStarShorthand(String s) {
        String res="";
        int max=1;
        char ch;
        try {
            ch = s.charAt(0);
            s = s.substring(1);
        } catch (StringIndexOutOfBoundsException e){
            ch=';';
            return res;
        }
        for (char c: s.toCharArray()){
            if (c!=ch){
                if (max==1) {
                    res += ch;
                } else {
                    res += ch + "*" + max;
                    max=1;
                }
            } else {
                max++;
            }
            ch = c;
        }
        if (max == 1) {
            res += ch;
        } else {
            res += ch + "*" + max;
        }
    return  res;}
}

class exercise8 {
    public static boolean doesRhyme(String a, String b) {
        String la=a.split(" ")[a.split(" ").length-1].replaceAll("[!.?]","");
        String lb=b.split(" ")[b.split(" ").length-1].replaceAll("[!.?]","");
        return lb.toLowerCase().endsWith(la.toLowerCase());
    }
}

class exercise9 {
    public static boolean trouble(int a, int b) {
        int[] amas = new int[10];
        int[] bmas = new int[10];
        for (int i=0;i<=9;i++){
            amas[i]=0;
            bmas[i]=0;
        }
        while (a!=0 | b!=0){
            amas[a%10]++;
            bmas[b%10]++;
            a/=10;
            b/=10;
        }
        for (int i=0;i<=9;i++){
            if (amas[i]==3 & bmas[i]==2){
                return true;
            }
        }
    return false;}
}

class exercise10 {
    public static int countUniqueBooks(String stringSequence, char bookEnd) {
        int i = 0;
        String res = "";
        for (String s : stringSequence.split(Character.toString(bookEnd))) {
            if (i++ % 2 == 1) {
                for (char c : s.toCharArray()) {
                    if (res.indexOf(c) == -1)
                        res += c;
                }
            }
        }
    return res.length();}
}