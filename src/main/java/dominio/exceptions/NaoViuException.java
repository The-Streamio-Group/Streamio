package dominio.exceptions;

public class NaoViuException extends Exception {
    public NaoViuException() {
        System.out.println("Erro: Perfil Não viu esse Conteúdo!");
    }
}
