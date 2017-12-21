package cliente;

import servidor.IPList;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide extends Thread
{
    private IPList ips;
    ServerSide()
    {
        this.ips = null;
    }

    public void run()
    {
        try
        {
            ServerSocket response = new ServerSocket(54000);
            while (true)
            {
                Socket request = response.accept();
                ObjectInputStream in = new ObjectInputStream(request.getInputStream());
                this.ips = (IPList)in.readObject();
                in.close();
                request.close();
                System.out.println(this.ips);
            }
        }
        catch(Exception e)
        {
            System.err.println("Conexão no servidor encerrada por falha!");
        }
    }
}
