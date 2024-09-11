package dominio.dados.interfaces;

import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Perfil;

import java.util.UUID;

public interface IRepositorioPerfil {
    /*  Cadastra um novo objeto na lista do seu respectivo repositório
     *
     *  @param obj -> Objeto que vai ser cadastrado
     */
    void cadastrar(Perfil perfil);

    /*  Procura um objeto na lista do seu respectivo repositório baseado no id do objeto
     *
     *  @param id -> o id que define o objeto
     *
     *  @return o próprio objeto ou null se o id não existir na lista
     *
     *  @throws ElementoNaoExisteException -> Exceção caso o elemento não exista
     *
     */
    Perfil procurar(UUID id) throws ElementoNaoExisteException;

    /*  Reimplementação do método procurar, onde ele usa nickname de parâmetro
     *
     *  @param nickname -> nickname do Pefil
     *
     *  @return o próprio objeto ou null se o id não existir na lista
     *
     *  @throws ElementoNaoExisteException -> Exceção caso o elemento não exista
     *
     */

    Perfil procurarPorNick(String nickname) throws ElementoNaoExisteException;

    /*  Atualiza as informações do objeto
     *
     *  @param antigo -> Objeto antigo que vamos pegar o índice
     *
     *  @param novo -> Objeto novo que vai ser realocado no índice do @param antigo
     *
     *  @throws ElementoNaoExisteException -> Exceção caso o elemento não existe
     */
    void atualizar(UUID idAntigo, Perfil novo) throws ElementoNullException, ElementoNaoExisteException;

    /*  Remove o objeto da Lista de acordo com o id do objeto dado
     *
     *  @param id -> ID do objeto
     *
     *  @throws ElementoNullException -> Exceção caso o elemento seja nulo
     *
     */
    void remover(UUID id) throws ElementoNaoExisteException;

    /*  Método que informa se o objeto existe
     *
     *  @param id -> ID do objeto
     *
     *  @return true para se existe na lista, ou false para o contrário
     */
    boolean existe(UUID id);

    void removerConteudoDeTodosPerfis(Conteudo conteudo);
}
