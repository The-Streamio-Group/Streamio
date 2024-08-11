package dominio.exceptions;

public class ProdutoraNullException extends Exception{
    public ProdutoraNullException() {
        System.out.println("Erro: Produtora Inválida");
    }
}
