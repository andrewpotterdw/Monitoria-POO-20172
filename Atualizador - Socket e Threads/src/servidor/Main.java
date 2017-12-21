package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
                ArrayList<String> toBeRemoved = new ArrayList<String>();
                Socket request = response.accept();
                ObjectInputStream in = new ObjectInputStream(request.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(request.getOutputStream());
                responseMsg = (SimpleMessage)in.readObject();
                //in.close();
                IP = request.getInetAddress().getHostAddress();
                System.out.println("Mensagem de " + IP + ": " + responseMsg);
                out.flush();
                if(ips.addIP(IP))
                {
                    out.writeObject(new SimpleMessage("Seu IP agora faz parte da lista de conexões do servidor!"));
                    out.close();
                    in.close();
                    request.close();
                    for(int i = 0; i < ips.size(); i++)
                    {
                        ArrayList<Boolean> absent = new ArrayList<Boolean>();
                        SocketConnection client = new SocketConnection(ips.getIP(i), 54000, ips, absent);
                        client.run();
                        Thread.sleep(1000);
                        if(absent.size() == 1)
                        {
                            System.out.println(absent.get(0));
                            if(absent.get(0))
                            {
                                toBeRemoved.add(ips.getIP(i));
                            }
                        }
                    }
                    for (String aToBeRemoved : toBeRemoved) { ips.removeByIP(aToBeRemoved); }
                }
                else
                {
                    out.writeObject(new SimpleMessage("Seu IP já faz parte da lista de conexões do servidor!"));
                    out.close();
                    in.close();
                    request.close();
                }
            }
        }
        catch(Exception e)
        {
            System.err.println("Conexão encerrada por falha!");
        }
    }
}
