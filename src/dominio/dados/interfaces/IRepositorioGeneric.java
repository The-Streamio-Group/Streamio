package dominio.dados.interfaces;

import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;


/*  Interface genérica que nela, você acessa todos os repositórios
 *  T -> Type
 */
public interface IRepositorioGeneric<T>{


    /*  Cadastra um novo objeto na lista do seu respectivo repositório
     *
     *  @param obj -> Objeto que vai ser cadastrado
     */
    void cadastrar(T obj);

    /*  Procura um objeto na lista do seu respectivo repositório baseado no id do objeto
     *
     *  @param id -> o id que define o objeto
     *
     *  @return o próprio objeto ou null se o id não existir na lista
     *
     *  @throws ElementoNaoExisteException -> Exceção caso o elemento não exista
     *
     */
    T procurar(String id) throws ElementoNaoExisteException;

    /*  Procura o indice do objeto na lista do seu respectivo repositório baseado no id do objeto
     *
     *  @param id -> o id que define o objeto
     *
     *  @return o índice do objeto na lista do repositório
     *
     *  @throws ElementoNaoExisteException -> Exceção caso o elemento não exista
     */
    int procurarIndice(String id) throws ElementoNaoExisteException;

    /*  Atualiza as informações do objeto,
     *
     *  @param antigo -> Objeto antigo que vamos pegar o índice
     *
     *  @param novo -> Objeto novo que vai ser realocado no índice do @param antigo
     *
     *  @throws ElementoNaoExisteException -> Exceção caso o elemento não existe
     */
    void atualizar(T antigo, T novo) throws ElementoNullException;

    /*  Remove o objeto da Lista de acordo com o id do objeto dado
     *
     *  @param id -> ID do objeto
     *
     *  @throws ElementoNullException -> Exceção caso o elemento seja nulo
     *
     */
    void remover(String id) throws ElementoNaoExisteException;

    /*  Método que informa se o
     *
     *  @param id -> ID do objeto
     *
     *  @return true para se existe na lista, ou false para o contrário
     */
    boolean existe(String id);



}
