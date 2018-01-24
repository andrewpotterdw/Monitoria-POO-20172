package servidor; /**Pacote que engloba as classes da aplicação Servidor.*/

/**
 * @author Michael Almeida da Franca Monteiro
 * @version 1.0
 * 
 * Desenvolvida com a linguagem de programação orientada a objetos Java, as duas aplicações que compoem esse conjunto têm o propósito de demonstrar de forma básica, comunicações entre portas diferentes usando conexões Socket. 
 * Classe principal da aplicação Servidor.
 * */

import java.io.ObjectInputStream; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html*/
import java.io.ObjectOutputStream; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/io/ObjectOutputStream.html*/
import java.net.ServerSocket; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/net/ServerSocket.html*/
import java.net.Socket; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html*/
import java.util.ArrayList; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html*/

public class Main /**Classe principal da aplicação Servidor.*/
{
    public static void main(String[] args) /**Método principal da classe Main.*/
    {
        IPList ips = new IPList(); /**Criação de novo objeto do tipo IPList (Consultar no javadoca a parte relacionada a "IPList").*/
        System.out.println("Aplicação Servidor!"); /**Mensagem impressa no console para identificação da aplicação Servidor.*/
        String IP; /**Declaração de variável do tipo string.*/
        SimpleMessage responseMsg; /**Declaração de variável do tipo SimpleMessage (Consultar no javadoc a parte relacionada a "SimpleMessage").*/
        try /**Linha que estabelece o começo de um bloco de código ao ser analizado para encontrar exceções lançadas por erro ou propositalmente definidas pelo desenvolvedor.*/
        {
            ServerSocket response = new ServerSocket(64000); /**Criação de novo objeto do tipo ServerSocket passando por parâmetro o número da porta lógica onde rodará o servidor socket.*/
            while (true) /**While que determina loop infinito.*/
            { /**Chave de inicialização do bloco while*/
                ArrayList<String> toBeRemoved = new ArrayList<String>(); /**Criação de novo objeto do tipo ArrayList<String>.*/
                Socket request = response.accept(); /**Declaração de variável do tipo Socket que recebe conexão socket aceita pelo servidor socket.*/
                ObjectInputStream in = new ObjectInputStream(request.getInputStream()); /**Criação de novo objeto do tipo ObjectInputStream recebendo como parâmetro a via de saída de dados de request. (Consultar https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html)*/
                ObjectOutputStream out = new ObjectOutputStream(request.getOutputStream()); /**Criação de novo objeto do tipo ObjectOutputStream recebendo parâmetro a via de entrada de dados de request. (Consultar https://docs.oracle.com/javase/7/docs/api/java/io/ObjectOutputStream.html*/
                responseMsg = (SimpleMessage)in.readObject(); /**Leitura de objeto do tipo SimpleMessage recebido por meio da conexão socket aberta com o cliente e atribuição à variável responseMsg.*/
                //in.close();
                IP = request.getInetAddress().getHostAddress(); /**Atribuição de endereço IP recebido pelo client por meio da conexão socket.*/
                System.out.println("Mensagem de " + IP + ": " + responseMsg); /**Mensagem formatada impressa no console que junta a mensagem do cliente socket e o endereço IP do mesmo.*/
                out.flush(); /**Método para limpar possíveis buffers na stream de dados por onde será feita comunicações.*/
                if(ips.addIP(IP)) /**If para determinar se a operação de adicionar um IP no IPList deu certo.*/
                {
                    out.writeObject(new SimpleMessage("Seu IP agora faz parte da lista de conexões do servidor!")); /**Envio de objeto do tipo SimpleMessage para cliente socket.*/
                    out.close(); /**Fechamento de stream de saída de objetos na aplicação Servidor.*/
                    in.close(); /**Fechamento de stream de entrada de objetos na aplicação Servidor.*/
                    request.close(); /**Fechamento da conexão socket com o cliente.*/
                    for(int i = 0; i < ips.size(); i++) /**For que percorre o IPList ips.*/
                    {
                        ArrayList<Boolean> absent = new ArrayList<Boolean>(); /**Criação de novo objeto do tipo ArrayList<Boolean>.*/
                        SocketConnection client = new SocketConnection(ips.getIP(i), 54000, ips, absent); /**Criação de novo objeto do tipo SocketConnection passando por parâmetro o ip do cliente a se conectar, a porta para se conectar com o ServerSocket da aplicação cliente, o IPList com os ips registrados e um ArrayList<Boolean> para determinar quais endereços ips estão ausentes na rede (Consultar no javadoc a parte relacionada a "SocketConnection").*/
                        client.run(); /**Inicialização de classe executável do tipo SocketConnection.*/
                        Thread.sleep(1000); /**Pausa na execução da Thread principal durante 1000 milisegundos.*/
                        if(absent.size() == 1) /**If para determinar se o valor do tamanho do ArrayList<Boolean> absent é igual a 1.*/
                        {
                            System.out.println(absent.get(0)); /**Impressão no console do item no índice zero do ArrayList<Boolean> absent.*/
                            if(absent.get(0)) /**I para determinar se o item no índice 0 do ArrayList<Boolean> absent é true ou false.*/
                            {
                                toBeRemoved.add(ips.getIP(i)); /**Adição de string que representa um endereço IP na ArrayList<String> toBeRemoved.*/
                            } /**I para determinar se o item no índice 0 do ArrayList<Boolean> absent é true ou false.*/
                        } /**If para determinar se o valor do tamanho do ArrayList<Boolean> absent é igual a 1.*/
                    } /**For que percorre o IPList ips.*/
                    for (String aToBeRemoved : toBeRemoved) { ips.removeByIP(aToBeRemoved); } /**Foreach que percorre ArrayList<String> toBeRemoved e remove string aToBeRemoved que representa um endereço IP do IPList ips.*/
                } /**If para determinar se a operação de adicionar um IP no IPList deu certo.*/
                else /**Caso contrário da condição estabelecida pelo if acima.*/
                { /**Chave de incialização do bloco else*/
                    out.writeObject(new SimpleMessage("Seu IP já faz parte da lista de conexões do servidor!")); /**Escrita de objeto do tipo SimpleMessage e envio do mesmo para a aplicação Cliente.*/
                    out.close(); /**Fechamento de stream de saída na aplicação Servidor.*/
                    in.close(); /**Fechamento de stream de entrada na aplicação Servidor.*/
                    request.close(); /**Fechamento da conexão socket com a aplicação Cliente.*/
                } /**Chave de finalização do bloco else*/
            } /**Chave de finalização do bloco while*/
        } /**Bloco de código ao ser analizado para encontrar exceções lançadas por erro ou propositalmente definidas pelo desenvolvedor.*/
        catch(Exception e) /**Linha que estabelece início do bloco de código a ser executado caso no bloco de código englobado por "try" seja lançada uma exceção.*/
        {
            System.err.println("Conexão encerrada por falha!"); /**Mensagem de erro impressa no console especificando o tipo de erro vindo da exceção lançada no bloco englobado por "try".*/
        } /**Bloco de código a ser executado caso no bloco de código englobado por "try" seja lançada uma exceção.*/
    } /**Método principal da classe Main.*/
} /**Classe principal da aplicação Servidor.*/