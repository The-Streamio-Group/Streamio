package dominio;

public class Assinatura {
    private int assinaturaID;
    private String tipo;
    private boolean statusPagamento;

    public Assinatura(int id, String tipo, boolean statusPagamento) {
        this.assinaturaID = id;
        this.tipo = tipo;
        this.statusPagamento = statusPagamento;
    }


    public void verificarTipoPagamento(){
        /**/
    }

    public void verificarStatusPagamento(){
        /**/
    }

}
