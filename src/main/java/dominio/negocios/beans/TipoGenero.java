package dominio.negocios.beans;

public enum TipoGenero {
    ACAO("Ação"),
    ANIMACAO("Animação"),
    COMEDIA("Comédia"),
    DRAMA("Drama"),
    DOCUMENTARIO("Documentário"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    TERROR("Terror"),
    ROMANCE("Romance");

    private final String tipo;

    TipoGenero(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }
}
