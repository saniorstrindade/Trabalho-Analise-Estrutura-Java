package Algoritimos;

public class Ordenacao {
    
    
    public static class ResultadoOrdenacao {
        public long operacoes;
        
        public ResultadoOrdenacao(long operacoes) {
            this.operacoes = operacoes;
        }
    }

    // ordenação bubble sort
    public static ResultadoOrdenacao bubbleSort(int[] arr) {
        long count = 0;
        int n = arr.length;
        
        
        for (int i = 0; i < n - 1; i++) {
            
            for (int j = 0; j < n - 1 - i; j++) {
                count++;
                
                
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        
        return new ResultadoOrdenacao(count);
    }

    // ordenação insertion sort
    public static ResultadoOrdenacao insertionSort(int[] arr) {
        long count = 0;
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int chave = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] > chave) {
                count++;
                arr[j + 1] = arr[j];
                j--;
            }
            if (j >= 0) count++;
            
            arr[j + 1] = chave;
        }
        
        return new ResultadoOrdenacao(count);
    }

    // ordenação selection sort
    public static ResultadoOrdenacao selectionSort(int[] arr) {
        long count = 0;
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            
            for (int j = i + 1; j < n; j++) {
                count++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
        
        return new ResultadoOrdenacao(count);
    }

    // ordenação merger sort

    private static long mergeSortCount = 0;
    
    public static ResultadoOrdenacao mergeSort(int[] arr) {
        mergeSortCount = 0;
        mergeSortRecursivo(arr, 0, arr.length - 1);
        return new ResultadoOrdenacao(mergeSortCount);
    }
    
    private static void mergeSortRecursivo(int[] arr, int esquerda, int direita) {
        if (esquerda < direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            
            
            mergeSortRecursivo(arr, esquerda, meio);
            mergeSortRecursivo(arr, meio + 1, direita);
            
        
            merge(arr, esquerda, meio, direita);
        }
    }
    
    private static void merge(int[] arr, int esquerda, int meio, int direita) {
        
        int n1 = meio - esquerda + 1;
        int n2 = direita - meio;
        
        
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        
        for (int i = 0; i < n1; i++) {
            L[i] = arr[esquerda + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[meio + 1 + j];
        }
        
        
        int i = 0, j = 0;
        int k = esquerda;
        
        while (i < n1 && j < n2) {
            mergeSortCount++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        
        
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        
        
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    //ordenação quick sort
    
    private static long quickSortCount = 0;
    
    public static ResultadoOrdenacao quickSort(int[] arr) {
        quickSortCount = 0;
        quickSortRecursivo(arr, 0, arr.length - 1);
        return new ResultadoOrdenacao(quickSortCount);
    }
    
    private static void quickSortRecursivo(int[] arr, int baixo, int alto) {
        if (baixo < alto) {
            int pi = particionar(arr, baixo, alto);
            
            quickSortRecursivo(arr, baixo, pi - 1);
            quickSortRecursivo(arr, pi + 1, alto);
        }
    }
    
    private static int particionar(int[] arr, int baixo, int alto) {
        int pivo = arr[alto];
        int i = (baixo - 1);
        
        for (int j = baixo; j < alto; j++) {
            quickSortCount++;
            if (arr[j] < pivo) {
                i++;
                
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temp;
        
        return i + 1;
    }
}