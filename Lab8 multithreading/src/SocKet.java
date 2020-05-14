import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net. *;
public class SocKet {
    Socket socket;

    public SocKet (String host, int port) throws IOException {
        socket = new Socket(host,port);
        socket.setSoTimeout(10000);
    }
    public void setSoTimeout(int timeout) throws IOException{
        socket.setSoTimeout(timeout);

    }
    public InputStream getInputStream() throws IOException{
        return  socket.getInputStream();
    }
    public OutputStream getOutputStream() throws IOException{
        return socket.getOutputStream();
    }
    public void close() throws IOException{
        socket.close();
    }

}