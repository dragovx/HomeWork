import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Crawler {

    private int max_depth = 10;
    public static final String URL_PREFIX = "https://";

    public static void main(String args[]) throws Exception {
        String host = "nytimes.com";
        String host1 = host;
        URLDepthPair hm = new URLDepthPair(host1);
        Socket soc = new Socket(hm.getURLS(), 80);
        LinkedList<URLDepthPair> ll = new LinkedList<>();
        soc.setSoTimeout(2000);
        try {
            URL url = new URL(URL_PREFIX + host);
            try {
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                String string = reader.readLine();
                while (string != null) {
                    for (String reta: string.split("href=\""))
                        try {
                            if (string.contains("href=\"https://") && reta.contains("https://")) {
                                hm = new URLDepthPair(reta.substring(0, reta.indexOf("\"")),(reta.substring(0, reta.indexOf("\"")).split("/").length - 3));
                                ll.add(hm);
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
        System.out.println(ll);
    }
}