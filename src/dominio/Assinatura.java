package dominio;
public class Assinatura {
    private int assinaturaID;
    private String tipo;
    private boolean statusPagamento;
    private Cartao cartaoUsuario;

    public Assinatura(int assinaturaID, String tipo, boolean statusPagamento, Cartao cartaoUsuario) {
        this.assinaturaID = assinaturaID;
        this.tipo = tipo;
        this.statusPagamento = statusPagamento;
        this.cartaoUsuario = cartaoUsuario;
    }

    // getters e setters
    public int getAssinaturaID() {
        return assinaturaID;
    }
    public void setAssinaturaID(int assinaturaID) {
        this.assinaturaID = assinaturaID;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean isStatusPagamento() {
        return statusPagamento;
    }
    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
    public Cartao getCartaoUsuario() {
        return cartaoUsuario;
    }
    public void setCartaoUsuario(Cartao cartaoUsuario) {
        this.cartaoUsuario = cartaoUsuario;
    }

    // Métodos específicos
    public void verificarTipoPagamento(Usuario usuario){

    };
}

