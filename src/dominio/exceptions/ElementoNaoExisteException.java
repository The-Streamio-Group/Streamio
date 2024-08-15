package dominio.exceptions;

public class ElementoNaoExisteException extends Exception {

    public ElementoNaoExisteException() {
        System.out.println("Erro: Elemento não existe!");
    }
}
