package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
    public static void main(String[] args)
    {
        IPList ips = new IPList();
        System.out.println("Aplicação Servidor!");
        String IP;
        SimpleMessage responseMsg;
        try {
            ServerSocket response = new ServerSocket(64000);
            while (true)
            {
                Socket request = response.accept();
                ObjectInputStream in = new ObjectInputStream(request.getInputStream());
                System.err.println("err7");
                ObjectOutputStream out = new ObjectOutputStream(request.getOutputStream());
                responseMsg = (SimpleMessage)in.readObject();
                in.close();
                System.out.println(responseMsg);
                IP = request.getInetAddress().getHostAddress();
                System.err.println("err8");
                out.flush();
                if(ips.addIP(IP))
                {
                    out.writeObject(new SimpleMessage("Seu IP agora faz parte da lista de conexões do servidor!"));
                    for(int i = 0; i < ips.size(); i++)
                    {
                        SocketConnection client = new SocketConnection(ips.getIP(i), 54000, ips);
                    }
                }
                else
                {
                    out.writeObject(new SimpleMessage("Seu IP já faz parte da lista de conexões do servidor!"));
                }
                out.close();
                request.close();
            }
        }
        catch(Exception e)
        {
            System.err.println("Conexão encerrada por falha!");
        }
    }
}
