public class URLDepthPair {
    int depth;
    String urls;

    URLDepthPair(String url, int depth) {
        urls=url;
        this.depth=depth;
    }
    URLDepthPair(String url) {
        urls=url;
        this.depth=0;
    }
    public int getDepth(){return depth;}

    public String getURLS(){return urls;}

    public String toString(){return urls +" "+ depth;}

}