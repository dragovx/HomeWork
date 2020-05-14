import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair {
    //private String url;
    String URL;
    int depth;
    public URLDepthPair(String URL, int depth){
        this.URL = URL;
        this.depth = depth;
    }
    public boolean Available(){
        String regex = "\\b(http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        return URL.matches(regex);
    }
    @Override
    public String toString() {
        return "URL = "+URL+" Depth = "+depth;
    }
    public String getURL(){
        try {
            java.net.URL url = new URL(this.URL);
            return url.getHost();
        }
        catch (MalformedURLException e) {
            System.err.println("MalformedURLException: " + e.getMessage());
            return null;
        }
    }

    public int depth(){
        return depth;
    }
}