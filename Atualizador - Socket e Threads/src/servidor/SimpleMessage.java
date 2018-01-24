package servidor; /**Pacote que engloba as classes da aplicação Servidor.*/

/**
 * @author Michael Almeida da Franca Monteiro
 * @version 1.0
 * 
 * Desenvolvida com a linguagem de programação orientada a objetos Java, as duas aplicações que compoem esse conjunto têm o propósito de demonstrar de forma básica, comunicações entre portas diferentes usando conexões Socket. 
 * Classe para armazenar uma mensagem simples do tipo string.
 * */

import java.io.Serializable; /**Para mais informações consulte o endereço web https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html*/

public class SimpleMessage implements Serializable /**Classe estática que vai armazenar uma mensagem simples do tipo string.*/
{
    private String simple; /**Declaração de variável do tipo string.*/

    public SimpleMessage(String simple) /**Método construtor da classe SimpleMessage passando por parâmetro string simple.*/
    {
        this.simple = simple; /**Atribuição da string simple recebida por parâmetro para a string simple da classe.*/
    } /**Método construtor da classe SimpleMessage passando por parâmetro string simple.*/

    public SimpleMessage() { } /**Método construtor da classe SimpleMessage sem passar parâmetros.*/

    private String getSimple() /**Método getSimple que retorna a string simple na classe.*/
    {
        return this.simple; /**Retorno de string simple.*/
    } /**Método getSimple que retorna a string simple na classe.*/

    public void setSimple(String simple) /**Método setSimple que recebe uma string como parâmetro e atribui à string simple da classe.*/
    {
        this.simple = simple; /**Atribuição de string simple recebida por parâmetro para string simple da classe.*/
    } /**Método setSimple que recebe uma string como parâmetro e atribui à string simple da classe.*/

    public String toString() /**Método toString que formata as informações da classe para impressão em formato mais organizado.*/
    {
        return this.simple; /**Retorno de string simple.*/
    } /**Método toString que formata as informações da classe para impressão em formato mais organizado.*/

    public boolean equals(SimpleMessage simpleS) { return(simpleS.getSimple().equals(this.simple)); } /**Método equals que recebe um objeto do tipo SimpleMessage como parâmetro para determinar se o objeto recebido tem os mesmos valores contidos na string simple.*/
    
} /**Classe estática que vai armazenar uma mensagem simples do tipo string.*/
