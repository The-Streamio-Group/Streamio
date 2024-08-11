package dominio.exceptions;

public class ProdutoraJaExisteException extends Exception{
    public ProdutoraJaExisteException() {
        System.out.println("Erro: Produtora Já existente");
    }
}
