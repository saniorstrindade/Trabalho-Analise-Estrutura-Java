package Estruturas;

public class Node {
    public int valor;
    public Node esquerda;
    public Node direita;
    public int altura; 
    
    public Node(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.altura = 1; 
    }
}