package Estruturas;

public class Vetor {
    private int[] dados;
    private int tamanho;
    private int capacidade;
    
    public Vetor(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.dados = new int[capacidadeInicial];
        this.tamanho = 0;
    }
    
    public void inserir(int valor) {
        if (tamanho == capacidade) {
            aumentarCapacidade();
        }
        
        dados[tamanho] = valor;
        tamanho++;
    }
    
    private void aumentarCapacidade() {
        capacidade = capacidade * 2;
        int[] novoArray = new int[capacidade];
        
        for (int i = 0; i < tamanho; i++) {
            novoArray[i] = dados[i];
        }
        
        dados = novoArray;
    }
    
    public int[] getDados() {
        int[] resultado = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            resultado[i] = dados[i];
        }
        return resultado;
    }
    
    public int getTamanho() {
        return tamanho;
    }
    
    public void limpar() {
        tamanho = 0;
    }
}