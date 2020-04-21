import java.io.*;
import java.net.*;
import java.util.*;

public class Crawler {

    private int max_depth = 1;
    public static final String URL_PREFIX = "https://";

    Crawler(String host) throws IOException {
        Socket soc = new Socket(host, 80);
        URLDepthPair hm = new URLDepthPair("https://"+ host + "/");
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
                                    if (hm.getDepth()<=max_depth & hm.getURLS().contains(host1)) {
                                        int sizea = not_viewed_url.size();
                                        int sizeb = viewed_url.size();
                                        boolean aState=false;
                                        boolean bState=false;
                                        int i=0;
                                        int j=0;
                                            while ((!aState & i<sizea)|(!bState & i<sizeb)){
                                                if (i<sizea) {
                                                    if (not_viewed_url.get(i).getURLS().contains(hm.getURLS())) {
                                                        aState = true;
                                                    }
                                                    i++;
                                                }
                                                if (j<sizeb){
                                                    if (viewed_url.get(j).getURLS().contains(hm.getURLS())) {
                                                        bState = true;
                                                    }
                                                    j++;
                                                }
                                            }
                                            if (!aState & !bState) {
                                                not_viewed_url.add(hm);
                                            }
                                        }
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
            viewed_url.add(not_viewed_url.getFirst());
            not_viewed_url.removeFirst();
            System.out.println("Проверенная cсылка: " + viewed_url.getLast().getURLS());
            System.out.println("Ссылок проверено: " + viewed_url.size());
        }
        viewed_url.sort(new Comparator<URLDepthPair>() {
            @Override
            public int compare(URLDepthPair o1, URLDepthPair o2) {
                return o1.getURLS().compareTo(o2.getURLS());
            }
        });
        Set<URLDepthPair> list = new HashSet<URLDepthPair>(viewed_url);
        viewed_url.clear();
        viewed_url.addAll(list);
        System.out.println(viewed_url);
    }

    public static void main(String args[]) throws Exception {
        new Crawler("nytimes.com");
    }
}