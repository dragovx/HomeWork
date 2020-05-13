import java.net.*;
import java.util.*;
import java.io.*;

public class Crawler {

    public static final String URL_PREFIX="http://";

    public static void main(String[] args) {
        new Crawler(new String[]{"http://www.mtuci.ru/","1"});
    }

    Crawler(String[] args){
        int max_depth = Integer.parseInt(args[1]);
        LinkedList<URLDepthPair> not_viewed_urls = new LinkedList<URLDepthPair>();
        LinkedList<URLDepthPair> viewed_urls = new LinkedList<URLDepthPair>();
        URLDepthPair first_depthpair = new URLDepthPair(args[0], 0);
        not_viewed_urls.add(first_depthpair);
        int j=0;
        while (not_viewed_urls.size() != 0) {
            URLDepthPair depthPair = not_viewed_urls.pop();
            System.out.println("Проверяется ссылка:" + '\t' + depthPair.getURL() + '\n'+
                    "Ссылок проверено:" + '\t' + ++j);
            viewed_urls.add(depthPair);
            int url_depth = depthPair.getDepth();
            LinkedList<String> linksList = new LinkedList<String>();
            try{
                linksList = getAllLinks(depthPair);
            } catch (IOException e){ }
            if (url_depth < max_depth) {
                for (int i=0;i<linksList.size();i++) {
                    String newURL = linksList.get(i);
                    if (!(not_viewed_urls.contains(new URLDepthPair(newURL, 0)) | viewed_urls.contains(new URLDepthPair(newURL, 0)))){
                        not_viewed_urls.add(new URLDepthPair(newURL, newURL.split("/").length-3));
                    }
                }
            }
        }

        int i=0;
        for (URLDepthPair list : viewed_urls) System.out.println(++i + ": " + list.getURL() + " [" + list.getDepth() + "]");
    }

    public static LinkedList<String> getAllLinks(URLDepthPair myDepthPair) throws IOException {
        LinkedList<String> URLs = new LinkedList<String>();
        Socket sock = new Socket(myDepthPair.getWebHost(), 80);
        sock.setSoTimeout(3000);
        PrintWriter myWriter = new PrintWriter(sock.getOutputStream(), true);
        myWriter.println("GET " + myDepthPair.getDocPath() + " HTTP/1.1");
        myWriter.println("Host: " + myDepthPair.getWebHost());
        myWriter.println("Connection: close");
        myWriter.println();
        BufferedReader BuffReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        String line;
        while (true) {
            line=BuffReader.readLine();
            if (line==null){ break; }
            for (String reta : line.split("href=\"")) {
                if (line.contains("a href=\"") & reta.startsWith(URL_PREFIX)){
                    try {
                        String Link = (reta.substring(0, reta.indexOf("\"")));
                        URLs.add(Link);
                    }catch (IndexOutOfBoundsException e){
                        System.err.println(e);
                        break;
                    }
                }
            }
        }
        sock.close();
        BuffReader.close();
        return URLs;
    }

}