import java.util.LinkedList;

public class URLPool {
    private LinkedList<URLDepthPair> listNotRun = new LinkedList<>();
    private LinkedList<URLDepthPair> resultlist = new LinkedList<>();
    private int busyTh1reads=0;
    private int waitingThreads=0;
    private int count;
    public URLPool(int count){
        this.count = count;
    }
    public synchronized URLDepthPair get(){
        if (listNotRun.size() == 0) {
            try {
                waitingThreads++;
                this.wait();

            }
            catch (InterruptedException e) {
                System.err.println("MalformedURLException: " + e.getMessage());
                return null;
            }
        }
        resultlist.add(listNotRun.getFirst());
        return listNotRun.removeFirst();
    }
    public synchronized int getWaitingThreads(){
        return waitingThreads;
    }
    public synchronized LinkedList<URLDepthPair> getResultlist(){
        return resultlist;
    }
    public synchronized void set(LinkedList<URLDepthPair> newLinks){
        if (newLinks.size()>0) {
            if (newLinks.getFirst().depth()>=count){
                resultlist.addAll(newLinks);
            }
            else{
                listNotRun.addAll(newLinks);
                for (int countSite=listNotRun.size(); countSite!=0 && waitingThreads!=0;countSite-- , waitingThreads--){
                    this.notify();
                }
            }
        }

    }
}