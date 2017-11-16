package cliente;

import servidor.IPList;
import servidor.SimpleMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("Aplicação Cliente!");
        socket();
        serverSocket();
    }

    public static void socket()
    {
        SimpleMessage requestMsg = new SimpleMessage("Por favor me ponha em sua lista de conexões!");
        try
        {
            Socket request = new Socket("169.254.23.78", 64000);
            ObjectOutputStream out = new ObjectOutputStream(request.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(request.getInputStream());
            out.flush();
            out.writeObject(requestMsg);
            out.close();
            requestMsg = (SimpleMessage)in.readObject();
            in.close();
            request.close();
        }
        catch(Exception e)
        {
            System.err.println("Conexão encerrada por falha!");
        }
        System.out.println(requestMsg);
    }

    public static void serverSocket()
    {
        IPList ips;
        try
        {
            ServerSocket response = new ServerSocket(54000);
            while(true)
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
            System.err.println("Conexão encerrada por falha!");
        }
    }
}

