import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aaa {
    public static void main(String[] args) {
        String text = "flllag";
        Pattern pattern = Pattern.compile("[^aeiuyo].+[aeiuyo]");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(text.substring(matcher.start(), matcher.end()));
        }
    }
}
