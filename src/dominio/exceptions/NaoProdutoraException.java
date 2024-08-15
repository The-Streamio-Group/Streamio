package dominio.exceptions;

public class NaoProdutoraException extends Exception {
    public NaoProdutoraException() {
        System.out.println("Erro: Não é uma produtora!");
    }
}
