package dominio.GUI;

import dominio.dados.RepositorioUsuarioList;
import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.*;

public class TelaTexual {
    public static void main(String[] args) {


        ISistemaFachada novo = SistemaFachada.getInstance();

        Assinatura assinatura = new Assinatura("234567");
        Assinante charlie = new Assinante("charlie","charlie@gmail.com","234", assinatura);
        Assinante roberto = new Assinante("Roberto do Brega", "emaildoroberto@gmail.com","brega", new Assinatura());
        Produtora teste1 = new Produtora("nome", "emailteste", "123");
        Conteudo teste2 = new Conteudo("Titanic", "Muito bom", TipoGenero.TERROR, 2);
        Conteudo teste3 = new Conteudo("BalacoBaco", "2", TipoGenero.ANIMACAO, 2);
        Conteudo teste4 = new Conteudo("Rambo", "2", TipoGenero.ANIMACAO, 2);
        Conteudo teste5 = new Conteudo("Jujutsu Kaisen", "2", TipoGenero.ANIMACAO, 2);
        Conteudo teste6 = new Conteudo("Deadpool", "2", TipoGenero.ANIMACAO, 2);
        try {
            //Teste de instanciar uma produtora
            novo.cadastrarUsuario(teste1);
            novo.realizarLogin("emailteste", "123");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            novo.adicionarConteudo(teste2);
            novo.adicionarConteudo(teste3);
            System.out.println(novo.getUsuarioString("emailteste"));
            novo.removerConteudo("Titanic");
            novo.atualizarConteudo(teste3,teste4);
            novo.adicionarConteudo(teste5);
            novo.adicionarConteudo(teste6);
            System.out.println("\n"+ novo.gerarRelatorio());
            System.out.println(novo.getUsuarioString("emailteste"));
            novo.logoff();
            System.out.println("Usuário deslogado com sucesso!");


            //Teste de instanciar um Assinante
            novo.cadastrarUsuario(charlie);
            novo.realizarLogin("charlie@gmail.com","234");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            novo.assistirConteudo("Jujutsu Kaisen");
            novo.assistirConteudo("Deadpool");
            novo.adicionarFavorito("Deadpool");
            novo.realizarAvaliacao(9,"Deadpool");
            novo.realizarAvaliacao(2,"Jujutsu Kaisen");
            novo.logoff();
            System.out.println("Usuário deslogado com sucesso!");


            //Teste de notas computadas
            novo.realizarLogin("emailteste", "123");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            System.out.println("\n" + novo.gerarRelatorio());
            novo.logoff();
            System.out.println("Usuário deslogado com sucesso!");


            //Usuário que não possui Assinatura
            novo.cadastrarUsuario(roberto);
            novo.realizarAssinatura("emaildoroberto@gmail.com","2345-78");
            novo.realizarLogin("emaildoroberto@gmail.com","brega");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            novo.assistirConteudo("Rambo");
            novo.adicionarFavorito("Rambo");
            novo.realizarAvaliacao(7,"Rambo");
            novo.assistirConteudo("Deadpool");
            novo.realizarAvaliacao(1,"Deadpool");
            novo.logoff();
            System.out.println("Usuário deslogado com sucesso!");

            //Teste de notas computadas
            novo.realizarLogin("emailteste", "123");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            System.out.println("\n" + novo.gerarRelatorio());
            novo.logoff();
            System.out.println("Usuário deslogado com sucesso!");




        } catch (Exception ex) {
            System.out.println("ERRO!");
        }


    }
}

