package dominio.negocios.beans;

import java.time.LocalDate;

import java.util.UUID;

public class Assinatura {
    private final UUID assinaturaID;
    private boolean statusPagamento;
    private String numeroCartao;
    private LocalDate dataAssinatura;
    private final Assinante assinante;

    public Assinatura(String numeroCartao, Assinante assinante) {
        this.assinaturaID = UUID.randomUUID();
        this.statusPagamento = true;
        this.numeroCartao = numeroCartao;
        this.assinante = assinante;
    }

    //Getters e setters
    public String getAssinaturaID() {
        return assinaturaID.toString();
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public boolean isStatusPagamento() {
        return statusPagamento;
    }
    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDate getDataAssinatura() {
        return dataAssinatura;
    }
    public void setDataAssinatura(LocalDate dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public Assinante getUsuario() {
        return assinante;
    }

    @Override
    public String toString() {
        return "Assinatura{" +
                "statusPagamento=" + statusPagamento +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", dataAssinatura=" + dataAssinatura +
                ", assinante=" + assinante +
                '}';
    }

    // * statusPagamento torna-se false após 30 dias (LocalDate), sem que seja feita a renovação
    // * revisar LocalDate para que, quando a assinatura for feita, avançar no tempo para instanciar

}

