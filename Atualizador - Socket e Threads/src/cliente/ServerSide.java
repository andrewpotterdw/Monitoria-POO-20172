package cliente;

import servidor.IPList;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide implements Runnable
{
    ServerSide()
    {

    }

    public void run()
    {
        IPList ips;
        try
        {
            ServerSocket response = new ServerSocket(54000);
            while (true)
            {
                Socket request = response.accept();
                ObjectInputStream in = new ObjectInputStream(request.getInputStream());
                ips = (IPList)in.readObject();
                in.close();
                request.close();
                System.out.println(ips);
            }
        }
        catch(Exception e)
        {
            System.err.println("Conexão no servidor encerrada por falha!");
        }
    }
}
