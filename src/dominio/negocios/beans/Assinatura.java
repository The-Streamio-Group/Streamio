package dominio.negocios.beans;

import dominio.exceptions.CartaoNullException;
import dominio.exceptions.PerfilNullException;

public class Assinatura {
    private String assinaturaID; //Exemplo de Ideia pra facilitar a generalização do projeto
    private String tipo; //Pode ser retirado
    private boolean statusPagamento;
    private Cartao cartaoUsuario;

    public Assinatura(int assinaturaID, String tipo, boolean statusPagamento, Cartao cartaoUsuario) throws CartaoNullException {
        if(cartaoUsuario == null){throw new CartaoNullException(); }
        this.assinaturaID = "AS" + assinaturaID;
        this.tipo = tipo; //Pode ser retirado
        this.statusPagamento = statusPagamento;
        this.cartaoUsuario = cartaoUsuario;
    }

    // getters e setters
    public String getAssinaturaID() {
        return assinaturaID;
    }
    public void setAssinaturaID(String assinaturaID) {
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

