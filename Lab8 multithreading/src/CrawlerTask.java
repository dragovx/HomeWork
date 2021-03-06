import java.io.IOException;
import java.util.LinkedList;

public class CrawlerTask implements Runnable {
    URLDepthPair urlDepthPair;
    URLPool urlPool;
    LinkedList<URLDepthPair> newLinks;

    public CrawlerTask(URLPool urlPool) {
        this.urlPool = urlPool;
    }

    @Override
    public void run() {
        while (true) {
            urlDepthPair = urlPool.get();
            try {
                newLinks = new Crawlers(urlDepthPair.getURL(), urlDepthPair.getDepth()).getAllLinks(urlDepthPair.getDepth()+1);
                urlPool.set(newLinks);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
