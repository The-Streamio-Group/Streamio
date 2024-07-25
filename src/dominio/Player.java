package dominio;
import java.util.ArrayList;

public class Player {

   private int id;
   private Conteudo conteudo;
   private String Timestamp;

   public Player(int id, Conteudo conteudo, String Timestamp) {
      this.id = id;
      this.conteudo = conteudo;
      this.Timestamp = Timestamp;
   }

   // getters e setters
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public Conteudo getConteudo() {
      return conteudo;
   }
   public void setConteudo(Conteudo conteudo) {
      this.conteudo = conteudo;
   }

   // (adicionar) métodos específicos
   public void IniciarResumir() {};

   public void pausar() {};

   public void avancarTempo() {};

   public void voltarTempo() {};

   public void avancarEpisodio() {};

   public void voltarEpisodio() {};
}

