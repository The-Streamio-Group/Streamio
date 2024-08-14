package dominio.GUI;

import dominio.dados.RepositorioUsuarioList;
import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.negocios.beans.Produtora;
import dominio.negocios.beans.Usuario;

public class TelaTexual {
    public static void main(String[] args) {

          Usuario teste = new Produtora("Fernando", "fernando12@gmail.com","sigma123");
          Usuario teste2 = new Produtora("roberto", "roberto24@gmail.com","sigma123");

        IRepositorioGeneric<Usuario> novo = RepositorioUsuarioList.getInstance();

        novo.cadastrar(teste);
        boolean test = novo.existe("roberto24@gmail.com");



        System.out.println(test);
        }

    }

