package Estruturas;

public class ArvoreBinariaBusca {
    private Node raiz;
    public long operacoes;
    
    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.operacoes = 0;
    }
    
    // estou inserindo um valor na árvore
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }
    
    private Node inserirRecursivo(Node atual, int valor) {
        operacoes++;
        
        // Sempre lembrar fazer a comparação
        if (atual == null) {
            return new Node(valor);
        }
        
        // Caso não for nulo entra nesse if e faz a comparação
        if (valor < atual.valor) {
            atual.esquerda = inserirRecursivo(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = inserirRecursivo(atual.direita, valor);
        }
        
        return atual;
    }
    
    
    public boolean buscar(int valor) {
        operacoes = 0;
        return buscarRecursivo(raiz, valor);
    }
    
    private boolean buscarRecursivo(Node atual, int valor) {
        operacoes++;
        
        
        if (atual == null) {
            return false;
        }
        
        
        if (valor == atual.valor) {
            return true;
        }
        
        
        if (valor < atual.valor) {
            return buscarRecursivo(atual.esquerda, valor);
        } else {
            return buscarRecursivo(atual.direita, valor);
        }
    }
    
    
    public long getOperacoes() {
        return operacoes;
    }
    
    
    public void limpar() {
        raiz = null;
        operacoes = 0;
    }
}
