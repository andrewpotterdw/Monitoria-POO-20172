package servidor; /**Pacote que engloba as classes da aplicação Servidor.*/

/**
 * @author Michael Almeida da Franca Monteiro
 * @version 1.0
 * 
 * Desenvolvida com a linguagem de programação orientada a objetos Java, as duas aplicações que compoem esse conjunto têm o propósito de demonstrar de forma básica, comunicações entre portas diferentes usando conexões Socket. 
 * Classe para representar uma lista de IPs usando ArrayList para registro de IPs novos registrados na aplicação Servidor e envio da mesma lista para cada IP de dentro dela.
 * */

import java.io.Serializable; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html*/
import java.util.ArrayList; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html*/

public class IPList implements Serializable /**Classe estática que representa uma lista de IPs usando ArrayList.*/
{
    private ArrayList<String> IPs; /**Declaração do ArrayList que vai armazenar os IPs em forma de strings.*/

    IPList() /**Método construtor da classe IPList, o qual inicializa a variável IPs atribuindo à mesma um novo ArrayList vazio.*/
    {
        IPs = new ArrayList<String>(); /**Atribuição de novo objeto do tipo ArrayList<String> à variável IPs.*/
    } /**Método construtor da classe IPList, o qual inicializa a variável IPs atribuindo à mesma um novo ArrayList vazio.*/

    private ArrayList<String> getIPs() /**Método getIPs que retorna o objeto do tipo ArrayList<String> IPs.*/
    {
        return this.IPs; /**Retorno de objeto do tipo ArrayList<String>.*/
    } /**Método getIPs que retorna o objeto do tipo ArrayList<String> IPs.*/

    public void setIPs(ArrayList<String> IPs) /**Método setIPs que recebe um objeto do tipo ArrayList<String> e atribui-o à variável IPs.*/
    {
        this.IPs = IPs; /**Atribuição da variável IPs recebida pelo método à variável IPs da classe.*/
    } /**Método setIPs que recebe um objeto do tipo ArrayList<String> e atribui-o à variável IPs.*/

    boolean addIP(String IP) /**Método addIP que adiciona uma string que representa um endereço IP recebida como parâmetro pelo método ao ArrayList<String> IPs da classe.*/
    {
        boolean flag = false; /**Atribuição de valor booleano false a uma flag para controle do loop.*/
        for (String IP1 : this.IPs) /**Foreach percorrendo o ArrayList<String> IPs.*/
        {
            if (IP1.equals(IP)) /**If para determinar se a string IP recebida como parâmetro pelo método é igual à string do item relacionado ao índice do loop atual do foreach.*/
            {
                flag = true; /**Atribuição de valor booleano true a uma flag para controle do loop.*/
                break; /**Comando para quebrar o loop caso a string IP tenha valor igual à string do item relacionado ao índice do loop atual do foreach.*/
            } /**If para determinar se a string IP recebida como parâmetro pelo método é igual à string do item relacionado ao índice do loop atual do foreach.*/
        } /**Foreach percorrendo o ArrayList<String> IPs.*/
        if(flag) /**If para determinar se a flag tem valor true ou false.*/
        {
            return false; /**Retorno de valor booleano false para caso no ArrayList<String> IPs haja uma string com o mesmo valor da string recebida por parâmetro pelo método.*/
        } /**If para determinar se a flag tem valor true ou false.*/
        else /**Caso contrário da condição estabelecida pelo if acima.*/
        {
            this.IPs.add(IP); /**Adição da string que representa um endereço IP recebida por parâmetro pelo método para caso a flag tenha o valor booleano false, ou seja, caso no ArrayList<String> não haja uma string com o mesmo valor da string recebida por parâmetro pelo método.*/
            return true; /**Retorno de valor booleano true para caso no ArrayList<String> IPs não haja uma string com o mesmo valor da string recebida por parâmetro pelo método.*/ 
        } /**Caso contrário da condição estabelecida pelo if acima.*/
    } /**Método addIP que adiciona uma string que representa um endereço IP recebida como parâmetro pelo método ao ArrayList<String> IPs da classe.*/

    public void resetIPs() /**Método resetIPs que apaga os valores do ArrayList<String> IPs, atribuindo um novo objeto do tipo ArrayList<String>.*/
    {
        this.IPs = new ArrayList<String>(); /**Atribuição de novo objeto do tipo ArrayList<String> à variável IPs.*/
    } /**Método resetIPs que apaga os valores do ArrayList<String> IPs, atribuindo um novo objeto do tipo ArrayList<String>.*/

    void removeByIP(String IP) /**Método removeByIP que recebe uma string representando um endereço IP por parâmetro, compara o valor dela com os valores das strings presentes no objeto do tipo ArrayList<String> IPs, e caso encontre alguma com o mesmo valor, a string que está dentro do ArrayList<String> é excluída do mesmo.*/ 
    {
        boolean flag = false; /**Atribuição de valor booleano false para controle do loop.*/
        int i; /**Declaração de variável inteira i, a qual servirá para percorrer o ArrayList<String> IPs pelos índices.*/
        for(i = 0; i < this.IPs.size(); i++) /**For que percorre o ArrayList<String> IPs.*/
        {
            if(this.IPs.get(i).equals(IP)) /**If para determinar se o valor da string em determinado índice do ArrayList<String> IPs é igual ao valor da string recebida por parâmetro.*/
            {
                flag = true; /**Atribuição de valor booleano true à flag caso haja no ArrayList<String> IPs uma string com o mesmo valor da string recebida por parâmetro pelo método.*/
                break; /**Comando para quebrar o loop caso a string IP tenha valor igual à string do item relacionado ao índice do loop atual do for.*/
            } /**If para determinar se o valor da string em determinado índice do ArrayList<String> IPs é igual ao valor da string recebida por parâmetro.*/
        } /**For que percorre o ArrayList<String> IPs.*/
        if(flag) /**If para determinar se o valor da flag é true ou false.*/
        {
            this.IPs.remove(i); /**Remoção da string por meio do índice do ArrayList<String> IPs.*/
        } /**If para determinar se o valor da flag é true ou false.*/
    } /**Método removeByIP que recebe uma string representando um endereço IP por parâmetro, compara o valor dela com os valores das strings presentes no objeto do tipo ArrayList<String> IPs, e caso encontre alguma com o mesmo valor, a string que está dentro do ArrayList<String> é excluída do mesmo.*/

    public boolean removeByIndex(int index) /**Método removeByIndex que recebe como parâmetro um número inteiro representando o índice de onde a string deve ser removida do ArrayList<String> IPs.*/
    {
        if(index > this.IPs.size() || index < 1) /**If para determinar se o valor de index é maior que o número de índices contidos no ArrayList<String> IPs ou menor que 1.*/
        {
            return false; /**Retorno de valor booleano falso caso o valor de index não se encaixar nos requisitos de funcionamento do método.*/
        } /**If para determinar se o valor de index é maior que o número de índices contidos no ArrayList<String> IPs ou menor que 1.*/
        else /**Caso contrário da condição estabelecida pelo if acima.*/
        {
            this.IPs.remove(index); /**Remoção de string do ArrayList<String> IPs por meio do índice recebido por parâmetro pelo método.*/
            return true; /**Retorno de valor booleano true caso o valor do índice recebido por parâmetro exista no ArrayList<String> IPs.*/
        } /**Caso contrário da condição estabelecida pelo if acima.*/
    } /**Método removeByIndex que recebe como parâmetro um número inteiro representando o índice de onde a string deve ser removida do ArrayList<String> IPs.*/

    String getIP(int index) /**Método getIP que recebe um número inteiro por parâmetro que representa o índice da string contida no ArrayList<String> IPs que será retornada.*/
    {
        return IPs.get(index); /**Retorno do valor da string contida no índice recebido por parâmetro no ArrayList<String> IPs.*/
    } /**Método getIP que recebe um número inteiro por parâmetro que representa o índice da string contida no ArrayList<String> IPs que será retornada.*/

    int size() /**Método size que retorna o número de strings contidas no ArrayList<String> IPs.*/
    {
        return IPs.size(); /**Retorno do número de strings contidas no ArrayList<String> Ips.*/
    } /**Método size que retorna o número de strings contidas no ArrayList<String> IPs.*/

    public String toString() /**Método toString que formata as informações da classe para impressão em formato mais organizado.*/
    {
        StringBuilder sReturn = new StringBuilder("Lista de IPs: \n\n"); /**Criação de novo objeto do tipo StringBuilder para acumular as informações referentes à classe.*/
        for(int i = 0; i < this.IPs.size(); i++) /**For para percorrer o ArrayList<String> IPs e somar todas as string contidas nele na variável sReturn.*/
        {
            sReturn.append(i + 1).append(" - ").append(this.IPs.get(i)).append("\n"); /**Linha que soma as strings de forma padronizada por linha dentro da variável sReturn.*/
        } /**For para percorrer o ArrayList<String> IPs e somar todas as string contidas nele na variável sReturn.*/
        return sReturn.toString(); /**Retorno da string formatada para impressão das informações da classe.*/
    } /**Método toString que formata as informações da classe para impressão em formato mais organizado.*/

    public boolean equals(IPList IPL) /**Método equals que recebe um objeto do tipo IPList como parâmetro para determinar se o objeto recebido tem os mesmos valores contidos no ArrayList<String> IPs.*/
    {
        return IPL.getIPs().equals(this.IPs); /**Retorno de valores booleanos true ou false caso o objeto IPL do tipo IPList e o ArrayList<String> IPs forem iguais ou diferentes respectivamente.*/
    } /**Método equals que recebe um objeto do tipo IPList como parâmetro para determinar se o objeto recebido tem os mesmos valores contidos no ArrayList<String> IPs.*/
} /**Classe estática que representa uma lista de IPs usando ArrayList.*/
