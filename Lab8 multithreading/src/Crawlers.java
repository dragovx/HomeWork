import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawlers {
    int depth;
    SocKet socKet;
    String URL;
    BufferedReader bufferedReader;
    PrintWriter printWriter;

    public Crawlers(String url, int port, int depth) throws IOException {
        socKet = new SocKet(url,port);
        bufferedReader = new BufferedReader(new InputStreamReader(socKet.getInputStream()));
        printWriter = new PrintWriter(socKet.getOutputStream(), true);
        this.depth = depth;
        URL=url;
    }

    public LinkedList<URLDepthPair> StartScanner(int count) throws IOException{
        printWriter.println("GET / HTTP/1.1");
        //printWriter.println("Host: www.google.com:80");
        printWriter.println("Host:"+URL+":80");
        printWriter.println("Connection: Close");
        printWriter.println();
        LinkedList<URLDepthPair> resultlist = new LinkedList<>();
        String str;
        try {
            while ((str = bufferedReader.readLine()) != null) {
                while(str.contains("<a")){
                    while (str.indexOf(">", str.indexOf("<a"))==-1) str+=bufferedReader.readLine();

                    String link = str.substring(str.indexOf("<a"),str.indexOf(">", str.indexOf("<a")));
                    if (link.contains("http://")){
                        Pattern pattern= Pattern.compile("(http:\\/\\/[\\w\\-\\.!~?&=+\\*'(),\\/\\#\\:]+)((?!\\<\\/\\w\\>))*?");
                        Matcher matcher = pattern.matcher(link);
                        matcher.find();
                        String url = matcher.group();

                        URLDepthPair urlDepthPair = new URLDepthPair(url, count);
                        resultlist.add(urlDepthPair);
                    }
                    str=str.replace(link, "");
                }
            }
        }
        catch (Exception e){
            System.out.println(e+"  OK");
            socKet.close();
            return resultlist;
        }
        socKet.close();
        return resultlist;
    }

    public static void main(String[] args) throws IOException {
        args = new String[]{"http://mtuci.ru//", "1", "4"};
        URLDepthPair urlDepthPair = new URLDepthPair(args[0], 0);

        int countDepth = Integer.parseInt(args[1]);
        int threadscount = Integer.parseInt(args[2]);

        URLPool urlPool = new URLPool(countDepth);
        LinkedList<URLDepthPair> mainlist = new LinkedList<>();
        mainlist.add(urlDepthPair);
        urlPool.set(mainlist);

        LinkedList<Thread> crawlerTasks = new LinkedList<>();

        for (int i = 0; i < threadscount; i++) {
            crawlerTasks.add(new Thread(new CrawlerTask(urlPool, countDepth)));
            crawlerTasks.getLast().start();
        }
        while (urlPool.getWaitingThreads() != threadscount) {
        }
        for (int i = 0; i < crawlerTasks.size(); i++) {
            crawlerTasks.get(i).stop();
        }
        mainlist = urlPool.getResultlist();
        for (int i = 0; i < mainlist.size(); i++) {
            System.out.println(mainlist.get(i));
        }

    }
}