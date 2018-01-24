package servidor; /**Pacote que engloba as classes da aplicação Servidor.*/

/**
 * @author Michael Almeida da Franca Monteiro
 * @version 1.0
 * 
 * Desenvolvida com a linguagem de programação orientada a objetos Java, as duas aplicações que compoem esse conjunto têm o propósito de demonstrar de forma básica, comunicações entre portas diferentes usando conexões Socket. 
 * Classe executável que implementa Runnable, com objetivo de fazer a conexão socket com um cliente identificado por uma string representando um endereço IP.
 * */

import java.io.ObjectOutputStream; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/io/ObjectOutputStream.html*/
import java.net.Socket; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html*/
import java.util.ArrayList; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html*/

public class SocketConnection implements Runnable /**Classe executável para estabelecer conexão socket com um cliente especificado.*/
{
    private ArrayList<Boolean> absent; /**Declaração de variável do tipo ArrayList<Boolean>.*/
    private String IP; /**Declaração de variável do tipo string.*/
    private int port; /**Declaração de variável do tipo inteiro.*/
    private IPList ips; /**Declaração de variável do tipo IPList (Consultar no javadoc a parte relacionada a "IPList").*/

    SocketConnection(String IP, int port, IPList ips, ArrayList<Boolean> absent) /**Método construtor para SocketConnection que recebe como parâmetro uma string que representa um endereço IP, um número inteiro que representa a porta lógica da aplicação Cliente com a qual será aberta uma conexão socket, uma IPList com a lista de endereços IP registrados na aplicação Servidor a ser enviada para a aplicação Cliente e um ArrayList<Boolean> onde serão registrados os clientes que estão fora da rede.*/
    {
        this.IP = IP; /**Atribuição de variável do tipo string IP recebida por parâmetro para a variável do tipo string IP da classe.*/
        this.port = port; /**Atribuição de variável do tipo inteiro port recebido por parâmetro para a variável do tipo inteiro port da classe.*/
        this.ips = ips; /**Atribuição de variável do tipo IPList ips recebido por parâmetro para a variável do tipo IPList ips da classe.*/
        this.absent = absent; /**Atribuição de variável do tipo ArrayList<Boolean> absent recebida por parâmetro para a variável do tipo ArrayList<Boolean> absent da classe.*/
    } /**Método construtor para SocketConnection que recebe como parâmetro uma string que representa um endereço IP, um número inteiro que representa a porta lógica da aplicação Cliente com a qual será aberta uma conexão socket, uma IPList com a lista de endereços IP registrados na aplicação Servidor a ser enviada para a aplicação Cliente e um ArrayList<Boolean> onde serão registrados os clientes que estão fora da rede.*/

    public void run() /**Método a ser executado quando chamado por um objeto do tipo SocketConnection.*/
    {
        boolean returnValue = false; /**Atribuição de valor booleano false à variábel returnValue.*/
        try /**Linha que estabelece o começo de um bloco de código ao ser analizado para encontrar exceções lançadas por erro ou propositalmente definidas pelo desenvolvedor.*/
        {
            Socket request = new Socket(IP, port); /**Criação de novo objeto do tipo Socket passando por parâmetro string representando o endereço IP de destino da conexão (Cliente) e um inteiro representando a porta a ser acessada na aplicação Cliente.*/
            ObjectOutputStream out = new ObjectOutputStream(request.getOutputStream()); /**Criação de novo objeto do tipo ObjectInputStream recebendo como parâmetro a via de saída de dados de request. (Consultar https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html)*/
            out.flush(); /**Método para limpar possíveis buffers na stream de dados por onde será feita comunicações.*/
            out.writeObject(this.ips); /**Escrita de objeto do tipo IPList para aplicação destino da conexão (Cliente).*/
            out.close(); /**Fechamento de stream de saída na aplicação Servidor.*/
            request.close(); /**Fechamento de conexão socket com a aplicação Cliente.*/
        } /**Linha que estabelece o começo de um bloco de código ao ser analizado para encontrar exceções lançadas por erro ou propositalmente definidas pelo desenvolvedor.*/
        catch(Exception e) /**Linha que estabelece início do bloco de código a ser executado caso no bloco de código englobado por "try" seja lançada uma exceção.*/
        {
            absent.add(true); /**Adição de marcador para identificar que o host do endereço IP o qual foi tentado conexão está indisponível na rede.*/
        } /**Linha que estabelece início do bloco de código a ser executado caso no bloco de código englobado por "try" seja lançada uma exceção.*/
    } /**Método a ser executado quando chamado por um objeto do tipo SocketConnection.*/

    private String getIP() /**Método getIP que retorna a string que representa o endereço IP da aplicação com a qual se tentará conexão socket.*/
    {
        return this.IP; /**Retorno de string que representa um endereço IP.*/
    } /**Método getIP que retorna a string que representa o endereço IP da aplicação com a qual se tentará conexão socket.*/

    private void setIP(String IP) { this.IP = IP; } /**Método setIP que recebe a variável do tipo string IP e atribui à variável do tipo string da classe.*/

    private int getPort() /**Método getPort que retorna a porta com a qual se tentará conexão com a aplicação Cliente via socket.*/
    {
        return this.port; /**Retorno de variável do tipo inteiro inteiro que representa a porta de conexão na qual está rodando o servidor socket na aplicação Cliente.*/
    } /**Método getPort que retorna a porta com a qual se tentará conexão com a aplicação Cliente via socket.*/

    public void setPort(int port) /**Método setPort que recebe uma variável do tipo inteiro port e que atribui à variável do tipo inteiro port da classe.*/
    {
        this.port = port; /**Atribuição da variável do tipo inteiro port recebida por parâmetro para a variável do tipo inteiro port da classe.*/
    } /**Método setPort que recebe uma variável do tipo inteiro port e que atribui à variável do tipo inteiro port da classe.*/

    public String toString() /**Método toString que formata as informações da classe para impressão em formato mais organizado.*/
    {
        return "IP: " + this.IP + "\nPorta: " + port; /**Retorno de variável do tipo string formatada com as informações da classe.*/
    } /**Método toString que formata as informações da classe para impressão em formato mais organizado.*/

    public boolean equals(SocketConnection socket) { return(socket.getIP().equals(this.IP) && socket.getPort() == this.port); } /**Método equals que recebe um objeto do tipo SocketConnection como parâmetro para determinar se o objeto recebido tem os mesmos valores contidos nas variáveis contidas na classe.*/
} /**Classe executável para estabelecer conexão socket com um cliente especificado.*/