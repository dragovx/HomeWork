import java.net.*;
import java.util.Objects;


public class URLDepthPair {

    private int depth;
    private String URL;

    public URLDepthPair(String URL, int depth) {
        this.depth = depth;
        this.URL = URL;
    }

    public String getURL() { return URL; }

    public int getDepth() { return depth; }

    public String toString() {
        String stringDepth = Integer.toString(depth);
        return URL + '\t' + stringDepth;
    }

    public String getDocPath() {
        try {
            return new URL(URL).getPath();
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public String getWebHost() {
        try {
            return new URL(URL).getHost();
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof URLDepthPair) {
            URLDepthPair o = (URLDepthPair)obj;
            return this.URL.equals(o.getURL());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
