package dominio.exceptions;

public class MesmoNomeException extends Exception {
    public MesmoNomeException() {
        System.out.println("Erro: Você tentou inserir o mesmo nome!");
    }
}
