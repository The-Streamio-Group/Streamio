package dominio.exceptions;

public class ConteudoJaExisteException extends Exception{
    public ConteudoJaExisteException() {
        System.out.println("Erro: Conteúdo já existente!");
    }
}
