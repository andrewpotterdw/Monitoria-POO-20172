package cliente; /**Pacote que engloba as classes da aplicação Cliente.*/

/**
 * @author Michael Almeida da Franca Monteiro
 * @version 1.0
 * 
 * Desenvolvida com a linguagem de programação orientada a objetos Java, as duas aplicações que compoem esse conjunto têm o propósito de demonstrar de forma básica, comunicações entre portas diferentes usando conexões Socket. 
 * Classe principal da aplicação Cliente.
 * */

import servidor.SimpleMessage; /**Classe criada pelo desenvolvedor para a aplicação, para mais informações sobre essa classe, consultar no javadoc a parte relacionada a "SimpleMessage".*/
import java.io.IOException; /**Para mais informações consulte o conteúdo no endereço web https://docs.oracle.com/javase/7/docs/api/java/io/IOException.html*/
import java.io.ObjectInputStream; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html*/
import java.io.ObjectOutputStream; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/io/ObjectOutputStream.html*/
import java.net.Socket; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html*/

public class Main /**Classe principal da aplicação Cliente.*/
{
    public static void main(String[] args) throws IOException /**Método principal da classe Main, lança exceção do tipo IOException. (Consultar https://docs.oracle.com/javase/7/docs/api/java/io/IOException.html)*/
    {
        System.out.println("Aplicação Cliente!"); /**Mensagem impressa no console para identificação da aplicação Cliente.*/
        ServerSide serverSocket = new ServerSide(); /**Criação de um novo objeto do tipo ServerSide. (Consultar no javadoc a parte relacionada a "ServerSide")*/
        serverSocket.start(); /**Inicialização do objeto serverSocket*/
        socket(); /**Invocação do método socket*/
    } /**Método principal da classe Main, lança exceção do tipo IOException. (Consultar https://docs.oracle.com/javase/7/docs/api/java/io/IOException.html)*/

    private static void socket() /**Método responsável por realizar a conexão Socket do Cliente com o Servidor.*/
    {
        SimpleMessage requestMsg = new SimpleMessage("Por favor me ponha em sua lista de conexões!"); /**Criação de novo objeto do tipo SimpleMessage. (Consultar no javadoc a parte relacionada a "SimpleMessage")*/
        try /**Linha que estabelece o começo de um bloco de código ao ser analizado para encontrar exceções lançadas por erro ou propositalmente definidas pelo desenvolvedor.*/
        {
            Socket request = new Socket("192.168.218.1", 64000); /**Criação de novo objeto do tipo Socket estabelecendo IP e porta de destino da conexão. (Consultar https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html)*/
            ObjectOutputStream out = new ObjectOutputStream(request.getOutputStream()); /**Criação de novo objeto do tipo ObjectInputStream recebendo como parâmetro a via de saída de dados de request. (Consultar https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html)*/
            ObjectInputStream in = new ObjectInputStream(request.getInputStream()); /**Criação de novo objeto do tipo ObjectOutputStream recebendo parâmetro a via de entrada de dados de request. (Consultar https://docs.oracle.com/javase/7/docs/api/java/io/ObjectOutputStream.html*/
            out.flush(); /**Método para limpar possíveis buffers na stream de dados por onde será feita comunicações.*/
            out.writeObject(requestMsg); /**Escrita de objeto para aplicação destino da conexão socket (Servidor).*/
            //out.close();
            requestMsg = (SimpleMessage)in.readObject(); /**Leitura de objeto recebido pela aplicação destino da conexão socket (Servidor).*/
            in.close(); /**Fechamento de stream de entrada de objetos na aplicação Cliente.*/
            request.close(); /**Fechamento da conexão socket com a aplicação destino (Servidor).*/
        } /**Bloco de código ao ser analizado para encontrar exceções lançadas por erro ou propositalmente definidas pelo desenvolvedor.*/
        catch(Exception e) /**Linha que estabelece início do bloco de código a ser executado caso no bloco de código englobado por "try" seja lançada uma exceção.*/
        {
            System.err.println("Conexão encerrada por falha!"); /**Mensagem de erro impressa no console especificando o tipo de erro vindo da exceção lançada no bloco englobado por "try".*/
        } /**Bloco de código a ser executado caso no bloco de código englobado por "try" seja lançada uma exceção.*/
        System.out.println(requestMsg); /**Impressão no console da mensagem recebida pela aplicação destino da conexão socket (Servidor) caso não tenha havido problemas na execução do código relacionado a envio e recebimento de objetos via socket.*/
    } /**Método responsável por realizar a conexão Socket do Cliente com o Servidor.*/

    /*public static void serverSocket()
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
            System.err.println("Conexão no servidor encerrada por falha!");
        }
    }*/
} /**Classe principal da aplicação Cliente.*/

