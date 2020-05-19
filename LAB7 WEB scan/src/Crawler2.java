import java.net.*;
import java.util.*;
import java.io.*;

public class Crawler2 {

    public static final String URL_PREFIX = "http://";
    URLDepthPair depthpair;

    Crawler2(String url, int depth) {
        depthpair = new URLDepthPair(url, depth);
    }

    public LinkedList<URLDepthPair> getAllLinks(int count) throws IOException {
        LinkedList<URLDepthPair> URLs = new LinkedList<>();
        Socket sock = new Socket(depthpair.getWebHost(), 80);
        sock.setSoTimeout(3000);
        PrintWriter myWriter = new PrintWriter(sock.getOutputStream(), true);
        myWriter.println("GET " + depthpair.getDocPath() + " HTTP/1.1");
        myWriter.println("Host: " + depthpair.getWebHost());
        myWriter.println("Connection: close");
        myWriter.println();
        BufferedReader BuffReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        String line;
        while (true) {
            line = BuffReader.readLine();
            if (line == null) {
                break;
            }
            for (String reta : line.split("href=\"")) {
                if (line.contains("a href=\"") & reta.startsWith(URL_PREFIX)) {
                    try {
                        String Link = (reta.substring(0, reta.indexOf("\"")));
                        URLs.add(new URLDepthPair(Link, count));
                    } catch (IndexOutOfBoundsException e) {
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

    public static void main(String[] args) {

        String[] arg1 = new String[]{"http://www.tadviser.ru/", "2", "2"};
        String[] arg2 = new String[]{"http://www.isua-mtuci.ru/", "2", "3"};
        String[] arg3 = new String[]{"http://www.mtuci.ru/", "3", "5"};

        args = arg1;

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
        List<URLDepthPair> list = new LinkedList<>(mainlist);
        list.sort(new MySalaryComp());
        System.out.format("%4s ","N");
        System.out.format("|%-6s|"," Глубина ");
        System.out.println(" Проверенные ссылки");
        int i=0;
        for (URLDepthPair depthPair : list) {
            System.out.format("%4s", i++);
            System.out.println(" "+ depthPair);
        }

    }

}

class MySalaryComp implements Comparator<URLDepthPair> {

    @Override
    public int compare(URLDepthPair o1, URLDepthPair o2) {
        if (o1.getDepth() > o2.getDepth()) {
            return 1;
        } else {
            return -1;
        }
    }
}