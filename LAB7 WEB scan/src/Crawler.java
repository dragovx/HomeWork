import java.io.*;
import java.net.*;
import java.util.*;

public class Crawler {

    private int max_depth = 10;
    public static final String URL_PREFIX = "http://";

    Crawler(String host) throws IOException {
        Socket soc = new Socket(host, 80);
        URLDepthPair hm = new URLDepthPair(URL_PREFIX + host);
        LinkedList<URLDepthPair> viewed_url = new LinkedList<>();
        LinkedList<URLDepthPair> not_viewed_url = new LinkedList<>();
        not_viewed_url.add(hm);
        soc.setSoTimeout(2000);
        while ((not_viewed_url.getFirst()!=null)) {
            try {
                URL url = new URL(not_viewed_url.getFirst().getURLS());
                try {
                    LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                    String string = reader.readLine();
                    while (string != null) {
                        for (String reta : string.split("href=\""))
                            try {
                                if (string.contains("href=\"" + URL_PREFIX) & reta.startsWith(URL_PREFIX)) {
                                    hm = new URLDepthPair(reta.substring(0, reta.indexOf("\"")), (reta.substring(0, reta.indexOf("\"")).split("/").length - 3));
                                    if (!not_viewed_url.contains(hm)) {
                                        not_viewed_url.add(hm);
                                    }
                                    System.out.println(reta.substring(0, reta.indexOf("\"")));
                                }
                            } catch (StringIndexOutOfBoundsException e) {
                            }
                        string = reader.readLine();
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            soc.close();
            /*Set<URLDepthPair> list = new HashSet<URLDepthPair>(not_viewed_url);
            not_viewed_url.clear();
            not_viewed_url.addAll(list);
            not_viewed_url.sort(new Comparator<URLDepthPair>() {
                @Override
                public int compare(URLDepthPair o1, URLDepthPair o2) {
                    return o1.getURLS().compareTo(o2.getURLS());
                }
            });*/
            viewed_url.add(not_viewed_url.getFirst());
            not_viewed_url.getFirst();
        }
        System.out.println(viewed_url);
    }

    public static void main(String args[]) throws Exception {
        new Crawler("google.com");
    }
}