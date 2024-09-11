package dominio.exceptions;

public class JaFavoritoException extends Exception {
    public JaFavoritoException() {
        System.out.println("Erro: Esse conteúdo já é favorito");
    }
}
