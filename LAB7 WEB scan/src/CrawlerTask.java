import java.io.IOException;
import java.util.LinkedList;

public class CrawlerTask implements Runnable {
    URLDepthPair urlDepthPair;
    URLPool urlPool;
    LinkedList<URLDepthPair> newLinks;
    int count;

    public CrawlerTask(URLPool urlPool, int count) {
        this.urlPool = urlPool;
        this.count = count;
    }

    @Override
    public void run() {
        while (true) {
            urlDepthPair = urlPool.get();
            try {
                newLinks = new Crawler2(urlDepthPair.getURL(), urlDepthPair.getDepth()).getAllLinks(urlDepthPair.getDepth()+1);
                urlPool.set(newLinks);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
