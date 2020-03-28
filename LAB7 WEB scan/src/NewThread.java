import java.util.LinkedList;

public class NewThread implements Runnable {
    String host;
    Thread t;
    LinkedList<URLDepthPair> ll;
    boolean bool;
    NewThread(LinkedList<URLDepthPair> ll, String host) {
        this.ll=ll;
        this.host=host;
        t = new Thread(this);
        t.start();
    }
    public void run() {
        int size = ll.size();
        bool=false;
        for(int i = 0; i < size; i++) {
            if (ll.get(i).equals(host)){
                bool=true;
                break;
            }
        }
    }

    public boolean getState(){
        return bool;
    }

}