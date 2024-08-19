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
        charlie.adicionarPerfil(perfilTeste);

        Assinante roberto = new Assinante("Roberto do Brega", "emaildoroberto@gmail.com", "brega", new Assinatura());
        Perfil perfilTeste2 = new Perfil("amante", 12);
        roberto.adicionarPerfil(perfilTeste2);

        Produtora teste1 = new Produtora("nome", "emailteste", "123");

        Conteudo teste2 = new Conteudo("Titanic", "Muito bom", TipoGenero.TERROR, 2, 69);
        Conteudo teste3 = new Conteudo("BalacoBaco", "2", TipoGenero.ANIMACAO, 2, 68);
        Conteudo teste4 = new Conteudo("Rambo", "2", TipoGenero.ANIMACAO, 2, 69);
        Conteudo teste5 = new Conteudo("Jujutsu Kaisen", "2", TipoGenero.ANIMACAO, 2, 66);
        Conteudo teste6 = new Conteudo("Deadpool", "2", TipoGenero.ANIMACAO, 2, 120);
        ReprodutoraConteudo testefilme3 = new ReprodutoraConteudo(teste3, 60);
        ReprodutoraConteudo testefilme32 = new ReprodutoraConteudo(teste3, 32);
        ReprodutoraConteudo testefilme6 = new ReprodutoraConteudo(teste6, 33);
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
            novo.cadastrarPerfil(perfilTeste);
            novo.realizarLogin("charlie@gmail.com", "234");
            novo.trocarPerfil("DuasBala");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            novo.assistirConteudo(testefilme3);
            novo.adicionarFavorito(testefilme3);
            novo.realizarAvaliacao(new Avaliacao(9, perfilTeste), testefilme3);
            novo.logoff();
            System.out.println("Usuário deslogado com sucesso!");


            //Teste de notas computadas
            novo.realizarLogin("emailteste", "123");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            System.out.println(novo.gerarRelatorioProdutora());
            novo.logoff();

            //Teste de instanciar um Assinante
            novo.cadastrarUsuario(roberto);
            novo.realizarAssinatura(roberto.getUsuarioID(),"22222-432");
            novo.cadastrarPerfil(perfilTeste2);
            novo.realizarLogin("emaildoroberto@gmail.com", "brega");
            novo.trocarPerfil("amante");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            novo.assistirConteudo(testefilme6);
            novo.assistirConteudo(testefilme32);
            novo.realizarAvaliacao(new Avaliacao(2, perfilTeste2), testefilme6);
            novo.realizarAvaliacao(new Avaliacao(9, perfilTeste2), testefilme32);
            novo.logoff();
            System.out.println("Usuário deslogado com sucesso!");

            //Teste de notas computadas
            novo.realizarLogin("emailteste", "123");
            System.out.println("Usuário " + novo.getUsuariologado().getNickname() + " logado com sucesso!!");
            System.out.println(novo.gerarRelatorioProdutora());
            novo.logoff();



        } catch (Exception ex){}



    }
}

