package servidor;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketConnection implements Runnable
{
    String IP;
    int port;
    IPList ips;

    public SocketConnection(String IP, int port, IPList ips)
    {
        this.IP = IP;
        this.port = port;
        this.ips = ips;
    }

    public void run()
    {
        try
        {
            Socket request = new Socket(IP, port);
            ObjectOutputStream out = new ObjectOutputStream(request.getOutputStream());
            out.flush();
            out.writeObject(this.ips);
            out.close();
            request.close();
        }
        catch(Exception e)
        {
            System.err.println("Conexão encerrada por falha!");
        }

    }

    public String getIP()
    {
        return this.IP;
    }

    public void setIP(String IP)
    {
        this.IP = IP;
    }

    public int getPort()
    {
        return this.port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String toString()
    {
        return "IP: " + this.IP + "\nPorta: " + port;
    }

    public boolean equals(SocketConnection socket)
    {
          if(socket.getIP().equals(this.IP) && socket.getPort() == this.port)
          {
              return true;
          }
          else
          {
              return false;
          }
    }
}
