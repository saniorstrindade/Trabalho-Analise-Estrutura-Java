package Algoritimos;

public class Busca {
    // criei uma classe auxiliar para retornar status de busca e contagem de operações
    public static class ResultadoBusca {
        public boolean encontrado; // Indica se o valor foi encontrado.
        public long operacoes;     // Contador de operações realizadas.

        public ResultadoBusca(boolean encontrado, long operacoes) {
            this.encontrado = encontrado;
            this.operacoes = operacoes;
        }
    }

    
     // Implementa a Busca Sequencial O(n)
     
    public static ResultadoBusca buscaSequencial(int[] arr, int valor) {
        long count = 0;
        
        // Percorre o vetor até encontrar o valor ou chegar ao final.
        // fiz o for para percorrer o vetor até encontrar o valor ou chegar ao final
        for (int i = 0; i < arr.length; i++) {
            count++; 
            
            if (arr[i] == valor) {
                //retorna true
                return new ResultadoBusca(true, count);
            }
        }
        
        //  retorna false.
        return new ResultadoBusca(false, count);
    }

    
     // Implementa a Busca Binária O(log n)
    
    public static ResultadoBusca buscaBinaria(int[] arr, int valor) {
        int baixo = 0;
        int alto = arr.length - 1;
        long count = 0;

        // enquanto o índice baixo não ultrapassar o alto
        while (baixo <= alto) {
            count++; 

            int meio = baixo + (alto - baixo) / 2;

            // se o valor do meio for o valor procurado
            if (arr[meio] == valor) {
                return new ResultadoBusca(true, count);
            } 
            
            // 2. Valor é maior, ignora a metade esquerda
            else if (arr[meio] < valor) {
                baixo = meio + 1;
            } 
            
            // 3. Valor é menor, ignora a metade direita
            else {
                alto = meio - 1;
            }
        }

        //  não encontrado retorna false.
        return new ResultadoBusca(false, count);
    }
}