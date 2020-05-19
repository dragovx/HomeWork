import java.util.LinkedList;

public class URLPool {

    private LinkedList<URLDepthPair> listNotRun = new LinkedList<>();
    private LinkedList<URLDepthPair> resultlist = new LinkedList<>();
    private int waitingThreads = 0;
    private int count;

    public URLPool(int count) {
        this.count = count;
    }

    public synchronized URLDepthPair get() {
        if (listNotRun.size() == 0) {
            try {
                waitingThreads++;
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("MalformedURLException: " + e.getMessage());
                return null;
            }
        }
        resultlist.add(listNotRun.getFirst());
        return listNotRun.removeFirst();
    }

    public synchronized void set(LinkedList<URLDepthPair> newLinks) {
        for (URLDepthPair newLink : newLinks) {
            if (newLink.getDepth() >= count) {
                if (!resultlist.contains(newLink))
                    resultlist.add(newLink);
            } else {
                if (!(resultlist.contains(newLink) | listNotRun.contains(newLink)))
                    listNotRun.add(newLink);
                for (int countSite = listNotRun.size(); countSite != 0 && waitingThreads != 0; countSite--, waitingThreads--) {
                    this.notify();
                }
            }
        }
    }

    public synchronized int getWaitingThreads() {
        return waitingThreads;
    }

    public synchronized LinkedList<URLDepthPair> getResultlist() {
        return resultlist;
    }
}