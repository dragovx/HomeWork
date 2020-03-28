import java.io.*;
import java.net.*;
import java.util.*;

public class Crawler {

    private int max_depth = 2;
    public static final String URL_PREFIX = "https://";

    Crawler(String host) throws IOException {
        Socket soc = new Socket(host, 80);
        URLDepthPair hm = new URLDepthPair("https://"+ host);
        String host1 = host;
        LinkedList<URLDepthPair> viewed_url = new LinkedList<>();
        LinkedList<URLDepthPair> not_viewed_url = new LinkedList<>();
        not_viewed_url.add(hm);
        soc.setSoTimeout(2000);
        while ((!not_viewed_url.isEmpty())) {
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
                                    if (!not_viewed_url.contains(hm) & !viewed_url.contains(hm) & hm.getDepth()<max_depth & hm.getURLS().contains(host1)) {
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
            not_viewed_url.removeFirst();
            System.out.println(not_viewed_url);
            System.out.println("234");
            System.out.println(viewed_url);
        }
        viewed_url.sort(new Comparator<URLDepthPair>() {
            @Override
            public int compare(URLDepthPair o1, URLDepthPair o2) {
                return o1.getURLS().compareTo(o2.getURLS());
            }
        });
        System.out.println(viewed_url);
    }

    public static void main(String args[]) throws Exception {
        new Crawler("vk.com");
    }
}