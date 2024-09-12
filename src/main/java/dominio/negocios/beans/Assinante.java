package dominio.negocios.beans;


import java.util.ArrayList;
import java.util.List;


public class Assinante extends Usuario {
    private Assinatura assinatura;
    private List<Perfil> perfis;

    public Assinante(String nickname, String email, String senha, Assinatura assinatura) {
        super(nickname, email, senha);
        this.assinatura = assinatura;
        this.perfis = new ArrayList<>();
    }

    //Getters e setters
    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    //Métodos específicos
    public void adicionarPerfil(Perfil novo){
        this.perfis.add(novo);
    }

    public int tamanhoPerfis(){
        return this.perfis.size();
    }


    public void removerPerfil(Perfil p){
        this.perfis.remove(p);
    }

    public boolean possuiPerfil(Perfil p){
         if(this.perfis.contains(p)){
            return true;
        }
         return false;
    }

    @Override
    public String toString() {
        return "Assinante{" +
                "assinatura=" + assinatura +
                ", perfis=" + perfis +
                '}';
    }


}
