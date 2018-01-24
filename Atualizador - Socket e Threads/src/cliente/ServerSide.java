package cliente; /**Pacote que engloba as classes da aplicação Cliente.*/

/**
 * @author Michael Almeida da Franca Monteiro
 * @version 1.0
 * 
 * Desenvolvida com a linguagem de programação orientada a objetos Java, as duas aplicações que compoem esse conjunto têm o propósito de demonstrar de forma básica, comunicações entre portas diferentes usando conexões Socket. 
 * Classe que roda o servidor socket da aplicação Cliente.
 * */
import servidor.IPList; /**Classe criada pelo desenvolvedor para a aplicação, para mais informações sobre essa classe, consultar no javadoc a parte relacionada a "IPList".*/
import java.io.ObjectInputStream; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html*/
import java.net.ServerSocket; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/net/ServerSocket.html*/
import java.net.Socket; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html*/

public class ServerSide extends Thread /**Classe executável que extende Thread.*/
{
    private IPList ips; /**Declaração de objeto do tipo IPList (Consultar no javadoca a parte relacionada a "IPList")*/
    ServerSide() /**Método construtor da classe ServerSide, o qual atribui o valor null a um objeto do tipo IPList declarado anteriormente.*/
    {
        this.ips = null; /**Atribuição de valor vazio à variável ips.*/
    } /**Método construtor da classe ServerSide, o qual atribui o valor null a um objeto do tipo IPList declarado anteriormente.*/

    public void run() /**Método a ser executado quando chamado por um objeto do tipo ServerSide.*/
    {
        try /**Linha que estabelece o começo de um bloco de código ao ser analizado para encontrar exceções lançadas por erro ou propositalmente definidas pelo desenvolvedor.*/
        {
            ServerSocket response = new ServerSocket(54000); /**Criação de novo objeto do tipo ServerSocket com o endereço IP da máquina rodando a aplicação Cliente.*/
            while (true) /**Bloco while que define loop infinito para que sempre possa receber uma conexão socket.*/
            {
                Socket request = response.accept(); /**Declaração e atribuição de objeto do tipo socket, recebendo uma conexão socket aceita pelo objeto response do tipo ServerSocket.*/
                ObjectInputStream in = new ObjectInputStream(request.getInputStream()); /**Criação de novo objeto do tipo ObjectInputStream recebendo como parâmetro a via de entrada de dados de request*/
                this.ips = (IPList)in.readObject(); /**Leitura de objeto recebido pela conexão socket de outra aplicação usando cast para IPList sendo atribuído à variável ips.*/
                in.close(); /**Fechamento da stream de entrada.*/
                request.close(); /**Fechamento da conexão socket com a aplicação origem (Servidor).*/
                System.out.println(this.ips); /**Impressão no console do objeto ips.*/
            } /**Bloco while que define loop infinito para que sempre possa receber uma conexão socket.*/
        } /**Bloco de código ao ser analizado para encontrar exceções lançadas por erro ou propositalmente definidas pelo desenvolvedor.*/
        catch(Exception e) /**Linha que estabelece início do bloco de código a ser executado caso no bloco de código englobado por "try" seja lançada uma exceção.*/
        {
            System.err.println("Conexão no servidor encerrada por falha!"); /**Mensagem de erro impressa no console especificando o tipo de erro vindo da exceção lançada no bloco englobado por "try".*/
        } /**Bloco de código a ser executado caso no bloco de código englobado por "try" seja lançada uma exceção.*/
    } /**Método a ser executado quando chamado por um objeto do tipo ServerSide.*/
} /**Classe executável que extende Thread.*/