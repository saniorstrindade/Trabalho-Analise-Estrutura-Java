//Questões discursiva sobre arvore avl é código , fazer método publico e 
//método privado , podem fazer mais de um método dentro dela para facilitar , não envolve rotacionar arvore,
// método de inserção sem calcular altura 
   public class No {
   //criamos a classe nó com essa base através delam conseguimos fazer as rotações
      int valor;
       No esquerda;
       No direita;
       //construtor
       public  No(int valor){
         this.valor=valor;
       }
       
   }
   //para pode passar paramentro linha 17
    private No raiz;
    public void inserir(int valor){
      raiz=inserir(raiz, valor)
      }//criamos a classe privada e instanciamos
    private No inserir(No raiz, int valor){
    // verifica se a raiz é nula
      if (raiz== null){
      // caso seja retorna new no valor 
      return new No(valor) ;
      }
      if (raiz.valor < valor){
         raiz.direita=inserir(raiz.direita,valor)
       }else if (raiz.valor > valor){
         raiz.esquerda=inserir(raiz.esquerda,valor)
       }
         return raiz;