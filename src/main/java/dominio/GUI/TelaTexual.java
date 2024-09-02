package dominio.GUI;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.*;

public class TelaTexual {
    public static void main(String[] args) {

        //Tela de Teste para aplicações dos Controllers e seus atributos

        ISistemaFachada novo = SistemaFachada.getInstance();

        Assinatura assinatura = new Assinatura("234567");
        Assinante charlie = new Assinante("charlie", "charlie@gmail.com", "234", assinatura);
        Perfil perfilTeste = new Perfil("DuasBala", 20);

        Assinante roberto = new Assinante("Roberto do Brega", "emaildoroberto@gmail.com", "brega", new Assinatura());
        Perfil perfilTeste2 = new Perfil("amante", 12);

        Produtora teste1 = new Produtora("nome", "emailteste", "123");

        Conteudo teste2 = new Conteudo("Titanic", "Muito bom", TipoGenero.TERROR, "2", 69);
        Conteudo teste3 = new Conteudo("BalacoBaco", "2", TipoGenero.ANIMACAO, "2", 68);
        Conteudo teste4 = new Conteudo("Rambo", "2", TipoGenero.ANIMACAO, "2", 69);
        Conteudo teste5 = new Conteudo("Jujutsu Kaisen", "2", TipoGenero.ANIMACAO, "2", 66);
        Conteudo teste6 = new Conteudo("Deadpool", "2", TipoGenero.ANIMACAO, "2", 120);
        ReproducaoConteudo testefilme3 = new ReproducaoConteudo(teste3, 60,perfilTeste);
        ReproducaoConteudo testefilme32 = new ReproducaoConteudo(teste3, 32,perfilTeste);
        ReproducaoConteudo testefilme6 = new ReproducaoConteudo(teste6, 33, perfilTeste2);
        try {
            //Teste de instanciar uma produtora
            novo.cadastrarUsuario(teste1);
            novo.realizarLogin("emailteste", "123");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            novo.adicionarConteudo(teste2);
            novo.adicionarConteudo(teste3);

            novo.removerConteudo(teste2.getConteudoID());
            novo.adicionarConteudo(teste5);
            novo.adicionarConteudo(teste6);
            System.out.println("\n" + novo.gerarRelatorioProdutora());
            novo.logoff();
            System.out.println("Usuário deslogado com sucesso!");


            //Teste de instanciar um Assinante
            novo.cadastrarUsuario(charlie);
            novo.realizarLogin("charlie@gmail.com", "234");
            novo.cadastrarPerfil(perfilTeste);
            System.out.println(novo.getUsuariologado());
            novo.trocarPerfil("DuasBala");
            novo.mudarNomePerfil(novo.getPerfilLogado().getPerfilID(), "NovasBalas");
            System.out.println(novo.getUsuariologado());
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            novo.assistirConteudo(teste3,20);
            novo.adicionarFavorito(teste3);
            novo.realizarAvaliacao(new Avaliacao(9, perfilTeste),teste3.getConteudoID());
            novo.logoff();
            System.out.println("Usuário deslogado com sucesso!");


            novo.realizarLogin("charlie@gmail.com", "234");
            novo.trocarPerfil("NovasBalas");
            System.out.println(novo.getUsuariologado());
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            novo.logoff();

            //Teste de notas computadas
            novo.realizarLogin("emailteste", "123");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            System.out.println(novo.gerarRelatorioProdutora());
            novo.logoff();

            //Teste de instanciar um Assinante
            novo.cadastrarUsuario(roberto);
            novo.realizarAssinatura(roberto.getUsuarioID(),"22222-432");
            novo.realizarLogin("emaildoroberto@gmail.com", "brega");
            novo.cadastrarPerfil(perfilTeste2);
            novo.trocarPerfil("amante");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            novo.assistirConteudo(teste6,42);
            novo.assistirConteudo(teste5,32);
            novo.realizarAvaliacao(new Avaliacao(2, perfilTeste2), teste6.getConteudoID());
            novo.realizarAvaliacao(new Avaliacao(9, perfilTeste2), teste5.getConteudoID());
            novo.logoff();
            System.out.println("Usuário deslogado com sucesso!");

            //Teste de notas computadas
            novo.realizarLogin("emailteste", "123");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            System.out.println(novo.gerarRelatorioProdutora());
            novo.logoff();



        } catch (Exception ex){
            System.out.println("erro!");
        }



    }
}

