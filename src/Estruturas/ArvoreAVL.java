package Estruturas;

public class ArvoreAVL {
    private Node raiz;
    public long operacoes;
    
    public ArvoreAVL() {
        this.raiz = null;
        this.operacoes = 0;
    }
    
    private int altura(Node n) {
        if (n == null) {
            return 0;
        }
        return n.altura;
    }
    
    private int getFatorBalanceamento(Node n) {
        if (n == null) {
            return 0;
        }
        return altura(n.esquerda) - altura(n.direita);
    }
    
    private Node rotacaoDireita(Node y) {
        operacoes++;
        Node x = y.esquerda;
        Node T2 = x.direita;
        

        x.direita = y;
        y.esquerda = T2;
        

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        
        return x;
    }
    
    private Node rotacaoEsquerda(Node x) {
        operacoes++;
        Node y = x.direita;
        Node T2 = y.esquerda;
        
        y.esquerda = x;
        x.direita = T2;
        
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        
        return y;
    }
    
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }
    
    private Node inserirRecursivo(Node node, int valor) {
        operacoes++;
        
        if (node == null) {
            return new Node(valor);
        }
        
        if (valor < node.valor) {
            node.esquerda = inserirRecursivo(node.esquerda, valor);
        } else if (valor > node.valor) {
            node.direita = inserirRecursivo(node.direita, valor);
        } else {
            return node;
        }
        
        node.altura = 1 + Math.max(altura(node.esquerda), altura(node.direita));
        
        int balance = getFatorBalanceamento(node);
        
        
        if (balance > 1 && valor < node.esquerda.valor) {
            return rotacaoDireita(node);
        }
        
        if (balance < -1 && valor > node.direita.valor) {
            return rotacaoEsquerda(node);
        }
        
        
        if (balance > 1 && valor > node.esquerda.valor) {
            node.esquerda = rotacaoEsquerda(node.esquerda);
            return rotacaoDireita(node);
        }
        
        if (balance < -1 && valor < node.direita.valor) {
            node.direita = rotacaoDireita(node.direita);
            return rotacaoEsquerda(node);
        }
        
        return node;
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