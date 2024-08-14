package dominio.negocios.beans;


public class Avaliacao {
    private final String avaliacaoID;
    private int nota;
    private final Assinante assinante;
    private static int proxima = 1;


    public Avaliacao(int nota, Assinante assinante) {
        this.avaliacaoID = Integer.toString(proxima);
        this.nota = nota;
        this.assinante = assinante;
        proxima++;
    }

    //Getters e setters


    public String getAvaliacaoID() {
        return avaliacaoID;
    }

    public int getNota() {
        return nota;
    }
    public void setNota(int nota) {
        this.nota = nota;
    }

    public Assinante getUsuario() {
        return assinante;
    }



}
