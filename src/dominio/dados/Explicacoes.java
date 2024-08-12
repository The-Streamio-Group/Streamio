package dominio.dados;

public class Explicacoes {
    /*   //HORAS GASTAS NESSE PROJETO: 0 horas
     *
     * TODO: UUID ou concatenar ID's
     *
     * TODO: Se decidir UUID, estudar mais sobre e perguntar ao professor ideias
     *
     * TODO: Modificamos ArrayLists para Arrays estáticos tirando a nova classe Playlist
     *
     * TODO: Definir Classes de negócio
     *
     * TODO: Analisar quais repositórios vão ser colocados ou incorporados
     *          ex:Produtora e Conteudo; Usuario e Perfil com as Avaliações
     *
     * TODO: Terminar os códigos dos Repositórios
     *
     * TODO: Fazer uma GUI Textual e verificar os erros
     *
     * TODO: Definir quais as classes de negócios, tipo Cadastro, Login, Pagamento *Analisar Cartão*
     *
     *
     * 1. O sistema começa com duas abas perguntando se vc é produtor ou Usuario
     * 2. O sistema de Usuário com a criação de um usuário com seus dados e cartão (String) *(GUI)*
     *    |Usuario, Assinatura|
     * 4. O usuário é obrigado a criar um perfil inicial com seus dados *(GUI)*
     * 5. Antes de levar o usuário ao menu de conteúdos, ele deve começar no menu de perfis onde ele pode
     * adicionar mais perfis, editar, remover ou acessar configurações de usuário no popup *(GUI) (regra de negócio)*
     * 6. Acesso ao menu de conteúdos com o histórico, favoritos (inicialmente vazios) e ordenação de
     * conteúdos de acordo com o gênero e o ranking (sem diferenciação entre filme/série)
     * **Série ou documentário apresenta episódios e temporadas**
     * 7. O usuário vai acessar a barra de pesquisa para pesquisar um determinado conteúdo através do nome
     * ou inserindo um dos gêneros
     * 8. O sistema vai mostrar todos os conteúdos relacionados ao nome ou gênero digitado
     * 9. O usuário vai adicionar o conteúdo a sua lista de favoritos
     * 10. O usuário vai acionar o player do conteúdo selecionado, adicionando o conteúdo para o histórico
     * 11. O usuário vai realizar uma avaliação do conteúdo
     * 12. Sistema de logout *(GUI)*
     * 13. Sistema de login *(GUI)*
     *
     * Analisar Produtora
     *
     *
     *
     *
     * 1. O sistema começa com uma aba perguntando se você é produtor ou usuário da plataforma.
     * 1.1. Se for produtora:
     * Inicializar o processo de cadastro da produtora
     * Realizar o login automático após o cadastro OU Transferir para a página de login
     * Após o login, a produtora tem um menu próprio onde pode apenas adicionar, editar e remover conteúdos.
     * *Será necessário um popup para que a produtora possa alterar informações da conta?
     * *Como será a diferença do cadastro de uma produtora comparado com um usuário? (além de assinatura, etc)
     *
     * 1.2. Se for usuário:
     * Inicializar o processo de cadastro do usuário
     * Realizar o login automático após o cadastro OU Transferir para a página de login
     * [?] Realizar o pagamento da assinatura de forma simplificada
     * Após o login/pagamento, o usuário tem acesso ao menu com diversos conteúdos ordenados de acordo com TipoGenero
     * e anoLancamento(?), além do Histórico, Favoritos e Barra de Pesquisa (GUI)
     * O usuário acessa um conteúdo, sendo incorporado no watchHistory, além de favoritar outro conteúdo,
     * que é incorporado nos favoritos
     * Por fim, o usuário avalia um conteúdo com uma nota de 1 até 5
     *
     * 2. O usuário/produtora faz o processo de logout
     *
     *
     * Os conteúdos precisam ser pré-existentes, adicionaram pela produtora para que um usuário possa interagir com eles.
     * Incorporar Scanner, UUID, LocalDate, etc
     * Analisar assinatura com base no tempo e boolean (?)
     *
     *
     * RESUMIDO (Entrega 3):
     * Cadastro + Login de Produtora
     * Editar dados da Produtora (em um pop-up) [?]
     * Adicionar, editar e remover Conteúdos (que serão armazenados no sistema)
     * Imprimir relatório das views + avaliação (dos conteúdos relacionados à Produtora) [?]
     * Logout
     *
     * Cadastro + Login de Usuário
     * (Perfil morreu, f no chat)
     * Editar dados de Usuário (em um pop-up)
     * Adicionar e remover conteúdos (criados na primeira seção) para a lista de favoritos
     * Interação com conteúdo que automaticamente adicione o mesmo para o histórico + view [?]
     * Avaliação de um conteúdo
     * Logout
     *
     *
     *
     *
     *
     *
     *
     *
     */
}