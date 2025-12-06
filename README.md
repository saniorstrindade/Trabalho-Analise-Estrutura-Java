# 📘 Análise de Desempenho de Estruturas de Dados em Java

Projeto desenvolvido para a disciplina de **Estrutura de Dados**, realizando testes de desempenho entre **Vetor**, **Árvore Binária de Busca (ABB)** e **Árvore AVL**.

Também foram implementados **cinco algoritmos de ordenação** e **dois métodos de busca**.

---

## 📌 Descrição do Projeto

O objetivo foi implementar todas as estruturas **manualmente**, sem usar bibliotecas do Java (`ArrayList`, `TreeSet`, etc.), e comparar:

- ⏱️ Tempo de inserção
- 🔍 Tempo de busca
- 🔢 Número de operações
- 📊 Impacto da ordem dos dados
- ⚖️ Diferença entre estruturas balanceadas e não balanceadas

Além disso, foram feitos **testes de ordenação** em vetores de tamanhos variados.

---

## 📁 Estrutura do Projeto

```
src/
│
├── Algoritmos/
│   ├── Busca.java                  # Busca sequencial e binária
│   └── Ordenacao.java              # 5 algoritmos de ordenação implementados
│
├── Estruturas/
│   ├── Vetor.java                  # Vetor dinâmico feito manualmente
│   ├── ArvoreBinariaBusca.java     # Implementação da ABB
│   ├── ArvoreAVL.java              # Implementação da AVL com rotações
│   └── Node.java                   # Nó usado pelas árvores
│
├── Main.java                       # Executa todos os testes e medições
└── README.md
```

---

## ▶️ Como Compilar

### Opção 1 — Terminal / CMD

```bash
cd src
javac Main.java
```

### Opção 2 — VS Code / IntelliJ / Eclipse

1. Abra a pasta do projeto
2. Compile normalmente (a IDE detecta automaticamente os arquivos)

---

## ▶️ Como Executar

### Terminal:

```bash
cd src
java Main
```

### IDE:

- Execute a classe `Main.java` (botão Run ▶️)

---

## 📊 Resultados Gerados

O programa produz tabelas e métricas como:

- ⏱️ **Tempo de inserção** (ms)
- 🔍 **Tempo de busca** (ms)
- 🔢 **Operações realizadas** (OP)
- 📊 Comparação entre **ABB x AVL**
- 🔄 **Bubble Sort x Merge Sort**

> **Nota:** Todos os resultados são calculados usando **média de 5 execuções**.

---

## 🏗️ Estruturas Implementadas

### 📦 Vetor Dinâmico

- Crescimento automático
- Inserção **O(1)** amortizado
- Busca Sequencial **O(n)**
- Busca Binária **O(log n)**

### 🌳 ABB (Árvore Binária de Busca)

- Inserção/Busca **O(log n)** no caso médio
- Pode degenerar (pior caso **O(n)**)

### ⚖️ AVL

- Sempre balanceada
- Inserção/Busca garantidos em **O(log n)**
- Implementação completa das **rotações**

---

## 🔄 Algoritmos de Ordenação

### Testados no relatório:

- **Bubble Sort** — O(n²)
- **Merge Sort** — O(n log n)

### Implementados como estudo:

- Insertion Sort
- Selection Sort
- Quick Sort

> **Importante:** Todos foram feitos **manualmente**, sem `Arrays.sort()`.

---

## 📈 Conjuntos de Teste

Foram utilizados **três estados diferentes** do vetor:

1. **Ordenado** (0, 1, 2, …)
2. **Inverso** (n, n-1, n-2, …)
3. **Aleatório** (Fisher-Yates, sem valores repetidos)

### Tamanhos testados:

- 100 elementos
- 1.000 elementos
- 10.000 elementos

> Cada teste foi executado **5 vezes** (com aquecimento da JVM).

---

## 🔧 Observações Técnicas

✅ Sem uso de estruturas prontas (`ArrayList`, `TreeSet`, `Collections`…)  
✅ Função própria de cópia (`copiarArray()`)  
✅ Medição usando `System.nanoTime()`  
✅ Contador de operações para validar **Big-O**  
✅ Tratamento contra duplicatas nos vetores aleatórios  
✅ Rotações simples e duplas na AVL foram implementadas manualmente

---

## 👨‍💻 Autor

**Sânio Rodrigues Silva Trindade**  
4º Período  
Trabalho acadêmico – Estrutura de Dados – 2025

---

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais.