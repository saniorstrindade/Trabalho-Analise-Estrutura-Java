import Algoritimos.Busca;
import Algoritimos.Ordenacao;
import Estruturas.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int[] TAMANHOS = {100, 1000, 10000};
    private static final int REPETICOES = 5;
    
    private static final double NANO_TO_MILLI = 1_000_000.0; 

    public static void main(String[] args) {
        System.out.println("=".repeat(120));
        System.out.println("ANÁLISE DE DESEMPENHO DE ESTRUTURAS DE DADOS EM JAVA");
        System.out.println("Medições de Tempo (ms) e Operações (Op) - Média de " + REPETICOES + " execuções");
        System.out.println("=".repeat(120));

        for (int tamanho : TAMANHOS) {
            System.out.println("\n" + "=".repeat(120));
            System.out.println("TAMANHO: " + tamanho + " elementos");
            System.out.println("=".repeat(120));

            testarOrdemOrdenada(tamanho);
            testarOrdemInversa(tamanho);
            testarOrdemAleatoria(tamanho);
        }

        System.out.println("\n" + "=".repeat(120));
        System.out.println("TESTES CONCLUÍDOS!");
        System.out.println("=".repeat(120));
    }

    

    private static void testarOrdemOrdenada(int tamanho) {
        System.out.println("\n--- ORDEM DE INSERÇÃO: ORDENADA ---");
        int[] dados = gerarDadosOrdenados(tamanho);
        executarTestes(dados, tamanho, "Ordenada");
    }

    private static void testarOrdemInversa(int tamanho) {
        System.out.println("\n--- ORDEM DE INSERÇÃO: INVERSA ---");
        int[] dados = gerarDadosInversos(tamanho);
        executarTestes(dados, tamanho, "Inversa");
    }

    private static void testarOrdemAleatoria(int tamanho) {
        System.out.println("\n--- ORDEM DE INSERÇÃO: ALEATÓRIA ---");
        int[] dados = gerarDadosAleatorios(tamanho);
        executarTestes(dados, tamanho, "Aleatória");
    }

    

    private static void executarTestes(int[] dados, int tamanho, String ordem) {
        testarVetor(dados, tamanho, ordem);
        testarArvoreBinaria(dados, tamanho, ordem);
        testarArvoreAVL(dados, tamanho, ordem);
    }

    // teste vetor

    private static void testarVetor(int[] dados, int tamanho, String ordem) {
        System.out.println("\n>> VETOR");

        
        long tempoInsercao = 0;
        for (int i = 0; i < REPETICOES; i++) {
            Vetor vetor = new Vetor(tamanho);
            long inicio = System.nanoTime();
            for (int valor : dados) {
                vetor.inserir(valor);
            }
            long fim = System.nanoTime();
            tempoInsercao += (fim - inicio);
        }
        double mediaInsercao = tempoInsercao / (double) REPETICOES / NANO_TO_MILLI;
        System.out.printf("  Inserção (O(1) amortizado): %.4f ms%n", mediaInsercao);

        
        Vetor vetor = new Vetor(tamanho);
        for (int valor : dados) {
            vetor.inserir(valor);
        }
        int[] array = vetor.getDados();

        
        testarBuscaSequencial(array, dados);

        
        testarOrdenacao(dados);

        
        int[] arrayOrdenado = copiarArray(dados);
        Ordenacao.quickSort(arrayOrdenado); 
        testarBuscaBinaria(arrayOrdenado, dados);
    }
        
            
    private static void testarBuscaSequencial(int[] array, int[] dadosOriginais) {
        System.out.println("  Busca Sequencial (O(N)):");
        
        int primeiro = dadosOriginais[0];
        int ultimo = dadosOriginais[dadosOriginais.length - 1];
        int meio = dadosOriginais[dadosOriginais.length / 2];
        int inexistente = -1;
        
        // Geração de 3 elementos aleatórios existentes
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int tamanho = dadosOriginais.length;
        int valorAleatorio1 = dadosOriginais[random.nextInt(0, tamanho)];
        int valorAleatorio2 = dadosOriginais[random.nextInt(0, tamanho)];
        int valorAleatorio3 = dadosOriginais[random.nextInt(0, tamanho)];
        
        medirBuscaSequencial(array, primeiro, "Primeiro");
        medirBuscaSequencial(array, ultimo, "Último");
        medirBuscaSequencial(array, meio, "Meio");
        
       
        medirBuscaSequencial(array, valorAleatorio1, "Aleatório 1");
        medirBuscaSequencial(array, valorAleatorio2, "Aleatório 2");
        medirBuscaSequencial(array, valorAleatorio3, "Aleatório 3");
        
        medirBuscaSequencial(array, inexistente, "Inexistente");
    }
    
        
    private static void medirBuscaSequencial(int[] array, int valor, String rotulo) {
        long tempoTotal = 0;
        long operacoesTotal = 0;
        
        for (int i = 0; i < REPETICOES; i++) {
            long inicio = System.nanoTime();
            Busca.ResultadoBusca resultado = Busca.buscaSequencial(array, valor); 
            long fim = System.nanoTime();
        
            tempoTotal += (fim - inicio);
            operacoesTotal += resultado.operacoes;
        }
        
        double mediaTempo = tempoTotal / (double) REPETICOES / NANO_TO_MILLI;
        long mediaOperacoes = operacoesTotal / REPETICOES;
        System.out.printf("    %-18s: %.4f ms | Op: %d%n", rotulo + " elemento", mediaTempo, mediaOperacoes);
    }
    
    private static void testarOrdenacao(int[] dados) {
        System.out.println("  Ordenação (Testado em cópias dos dados originais):");
        medirOrdenacao(dados, "Bubble Sort", "bubble");
        medirOrdenacao(dados, "Merge Sort", "merge");
    }
    
        
    private static void medirOrdenacao(int[] dados, String rotulo, String algoritmo) {
        long tempoTotal = 0;
        long operacoesTotal = 0;
        
        for (int i = 0; i < REPETICOES; i++) {
            int[] copia = copiarArray(dados);
            Ordenacao.ResultadoOrdenacao resultado = null;
        
            long inicio = System.nanoTime();
            if (algoritmo.equals("bubble")) {
                resultado = Ordenacao.bubbleSort(copia);
            } else if (algoritmo.equals("merge")) {
                resultado = Ordenacao.mergeSort(copia);
            }
            long fim = System.nanoTime();
        
            tempoTotal += (fim - inicio);
            if (resultado != null) {
                operacoesTotal += resultado.operacoes;
            }
        }
        //calculo e registro da média
        double mediaTempo = tempoTotal / (double) REPETICOES / NANO_TO_MILLI;
        //usei NANO_TO_MILLI para converter nanosegundos em milisegundos
        long mediaOperacoes = operacoesTotal / REPETICOES;
        System.out.printf("    %-18s: %.4f ms | Op: %d%n", rotulo, mediaTempo, mediaOperacoes);
    }

    // funções de teste da busca binária

    private static void testarBuscaBinaria(int[] array, int[] dadosOriginais) {
        System.out.println("  Busca Binária (O(log N)):");
        
        int primeiro = dadosOriginais[0];
        int ultimo = dadosOriginais[dadosOriginais.length - 1];
        int meio = dadosOriginais[dadosOriginais.length / 2];
        int inexistente = -1;

        // Geração de 3 elementos aleatórios existentes, lembrar que os valores devem estar no array original
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int tamanho = dadosOriginais.length;
        
        int valorAleatorio1 = dadosOriginais[random.nextInt(0, tamanho)];
        int valorAleatorio2 = dadosOriginais[random.nextInt(0, tamanho)];
        int valorAleatorio3 = dadosOriginais[random.nextInt(0, tamanho)];

        medirBuscaBinaria(array, primeiro, "Primeiro");
        medirBuscaBinaria(array, ultimo, "Último");
        medirBuscaBinaria(array, meio, "Meio");

        // teste 3 elementos aleatórios
        medirBuscaBinaria(array, valorAleatorio1, "Aleatório 1");
        medirBuscaBinaria(array, valorAleatorio2, "Aleatório 2");
        medirBuscaBinaria(array, valorAleatorio3, "Aleatório 3");

        medirBuscaBinaria(array, inexistente, "Inexistente");
    }
    
    private static void medirBuscaBinaria(int[] array, int valor, String rotulo) {
        long tempoTotal = 0;
        long operacoesTotal = 0;

        for (int i = 0; i < REPETICOES; i++) {
            long inicio = System.nanoTime();
            Busca.ResultadoBusca resultado = Busca.buscaBinaria(array, valor); 
            long fim = System.nanoTime();

            tempoTotal += (fim - inicio);
            operacoesTotal += resultado.operacoes;
        }

        //cálculo e média de desempenho
        double mediaTempo = tempoTotal / (double) REPETICOES / NANO_TO_MILLI;

        long mediaOperacoes = operacoesTotal / REPETICOES;
        
        System.out.printf("    %-18s: %.4f ms | Op: %d%n", rotulo + " elemento", mediaTempo, mediaOperacoes);
    }

    private static void testarArvoreBinaria(int[] dados, int tamanho, String ordem) {
        System.out.println("\n>> ÁRVORE BINÁRIA DE BUSCA (ABB)");

        
        long tempoInsercao = 0;
        long operacoesInsercao = 0; 

        for (int i = 0; i < REPETICOES; i++) {
            ArvoreBinariaBusca abb = new ArvoreBinariaBusca();

            long inicio = System.nanoTime();
            for (int valor : dados) {
                abb.inserir(valor);
            }
            long fim = System.nanoTime();

            tempoInsercao += (fim - inicio);
            operacoesInsercao += abb.getOperacoes(); 
        }

        double mediaTempo = tempoInsercao / (double) REPETICOES / NANO_TO_MILLI;
        long mediaOperacoes = operacoesInsercao / REPETICOES;
        System.out.printf("  Inserção: %.4f ms | Op: %d%n", mediaTempo, mediaOperacoes);

        
        ArvoreBinariaBusca abb = new ArvoreBinariaBusca();
        for (int valor : dados) {
            abb.inserir(valor);
        }

        testarBuscaArvore(abb, dados);
    }

    private static void testarBuscaArvore(ArvoreBinariaBusca arvore, int[] dados) {
        System.out.println("  Busca:");

        int primeiro = dados[0];
        int ultimo = dados[dados.length - 1];
        int meio = dados[dados.length / 2];
        int inexistente = -1;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int tamanho = dados.length;
        
        int valorAleatorio1 = dados[random.nextInt(0, tamanho)];
        int valorAleatorio2 = dados[random.nextInt(0, tamanho)];
        int valorAleatorio3 = dados[random.nextInt(0, tamanho)];

        medirBuscaABB(arvore, primeiro, "Primeiro");
        medirBuscaABB(arvore, ultimo, "Último");
        medirBuscaABB(arvore, meio, "Meio");

        medirBuscaABB(arvore, valorAleatorio1, "Aleatório 1");
        medirBuscaABB(arvore, valorAleatorio2, "Aleatório 2");
        medirBuscaABB(arvore, valorAleatorio3, "Aleatório 3");

        medirBuscaABB(arvore, inexistente, "Inexistente");
    }

    private static void medirBuscaABB(ArvoreBinariaBusca arvore, int valor, String rotulo) {
        long tempoTotal = 0;
        long operacoesTotal = 0;

        for (int i = 0; i < REPETICOES; i++) {
            long inicio = System.nanoTime();
            arvore.buscar(valor);
            long fim = System.nanoTime();

            tempoTotal += (fim - inicio);
            operacoesTotal += arvore.getOperacoes();
        }
        double mediaTempo = tempoTotal / (double) REPETICOES / NANO_TO_MILLI;
        long mediaOperacoes = operacoesTotal / REPETICOES;
        
        System.out.printf("    %-18s: %.4f ms | Op: %d%n", rotulo + " elemento", mediaTempo, mediaOperacoes);
    }

    //teste arvore avl

    private static void testarArvoreAVL(int[] dados, int tamanho, String ordem) {
        System.out.println("\n>> ÁRVORE AVL");

        // medição de inserção
        long tempoInsercao = 0;
        long operacoesInsercao = 0; 

        for (int i = 0; i < REPETICOES; i++) {
            ArvoreAVL avl = new ArvoreAVL();

            long inicio = System.nanoTime();
            for (int valor : dados) {
                avl.inserir(valor);
            }
            long fim = System.nanoTime();

            tempoInsercao += (fim - inicio);
            operacoesInsercao += avl.getOperacoes(); 
        }

        double mediaTempo = tempoInsercao / (double) REPETICOES / NANO_TO_MILLI;
        long mediaOperacoes = operacoesInsercao / REPETICOES;
        System.out.printf("  Inserção: %.4f ms | Op: %d%n", mediaTempo, mediaOperacoes);

        
        ArvoreAVL avl = new ArvoreAVL();
        for (int valor : dados) {
            avl.inserir(valor);
        }

        
        testarBuscaArvoreAVL(avl, dados);
    }

    private static void testarBuscaArvoreAVL(ArvoreAVL avl, int[] dados) {
        System.out.println("  Busca:");

        int primeiro = dados[0];
        int ultimo = dados[dados.length - 1];
        int meio = dados[dados.length / 2];
        int inexistente = -1;

        // Geração de 3 elementos aleatórios existentes, lembrar que os valores devem estar no array original
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int tamanho = dados.length;
        
        int valorAleatorio1 = dados[random.nextInt(0, tamanho)];
        int valorAleatorio2 = dados[random.nextInt(0, tamanho)];
        int valorAleatorio3 = dados[random.nextInt(0, tamanho)];

        medirBuscaAVL(avl, primeiro, "Primeiro");
        medirBuscaAVL(avl, ultimo, "Último");
        medirBuscaAVL(avl, meio, "Meio");


        medirBuscaAVL(avl, valorAleatorio1, "Aleatório 1");
        medirBuscaAVL(avl, valorAleatorio2, "Aleatório 2");
        medirBuscaAVL(avl, valorAleatorio3, "Aleatório 3");

        medirBuscaAVL(avl, inexistente, "Inexistente");
    }

    //medição e impressão de tempo 
    private static void medirBuscaAVL(ArvoreAVL avl, int valor, String rotulo) {
        long tempoTotal = 0;
        long operacoesTotal = 0;

        for (int i = 0; i < REPETICOES; i++) {
            long inicio = System.nanoTime();
            avl.buscar(valor);
            long fim = System.nanoTime();

            tempoTotal += (fim - inicio);
            operacoesTotal += avl.getOperacoes();
        }

        //cálculo e média de desempenho
        double mediaTempo = tempoTotal / (double) REPETICOES / NANO_TO_MILLI;

        long mediaOperacoes = operacoesTotal / REPETICOES;
        
        System.out.printf("    %-18s: %.4f ms | Op: %d%n", rotulo + " elemento", mediaTempo, mediaOperacoes);
    }

    // geração dos dados de teste

    private static int[] gerarDadosOrdenados(int tamanho) {
        int[] dados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            dados[i] = i;  
        }
        return dados;
    }

    private static int[] gerarDadosInversos(int tamanho) {
        int[] dados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            dados[i] = tamanho - i - 1;  
        }
        return dados;
    }

    private static int[] gerarDadosAleatorios(int tamanho) {
        int[] dados = new int[tamanho];
        
        
        for (int i = 0; i < tamanho; i++) {
            dados[i] = i;  
        }
        
        
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = tamanho - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            
            int temp = dados[i];
            dados[i] = dados[j];
            dados[j] = temp;
        }
        
        return dados;
    }

    
    private static int[] copiarArray(int[] original) {
        int[] copia = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copia[i] = original[i];
        }
        return copia;
    }
}