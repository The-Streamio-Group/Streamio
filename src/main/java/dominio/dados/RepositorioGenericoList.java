package dominio.dados;

import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.negocios.beans.Perfil;

import javax.swing.text.Element;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Function;

public abstract class RepositorioGenericoList<T> {
    protected final ArrayList<T> objectList;

    // implementar em cada rep*
    protected abstract UUID getId(T item);

    public RepositorioGenericoList() { this.objectList = new ArrayList<T>(); }

    // CRUD - cadastrar, procurar, remover, atualizar, existe, procurarIndice

    // Object como parâmetro
    public void cadastrar(T object) {
        objectList.add(object);
    }

    // UUID como parâmetro
    public T procurar(UUID id) throws ElementoNaoExisteException {
        for (T item : objectList) {
            if (getId(item).equals(id)) {
                return item;
            }
        }
        throw new ElementoNaoExisteException();
    }

    public void remover(UUID id) throws ElementoNaoExisteException {
        T removido = procurar(id);
        if (removido == null) {
            throw new ElementoNaoExisteException();
        }
        this.objectList.remove(removido);
    }

    public void atualizar(UUID id, T object) throws ElementoNullException, ElementoNaoExisteException {
        if (object == null) {
            throw new ElementoNullException();
        }
        boolean antigoE = existe(id);
        if (antigoE) {
            int indice = procurarIndice(id);
            this.objectList.set(indice, object);
        } else {
            throw new ElementoNaoExisteException();
        }
    }

    public boolean existe(UUID id) {
        for (T object : this.objectList) {
            if (getId(object).equals(id)) {
                return true;
            }
        }
        return false;
    }

    private int procurarIndice(UUID id) throws ElementoNaoExisteException {
        for (int i = 0; i < this.objectList.size(); i++) {
            T item = objectList.get(i);
            if (getId(item).equals(id)) {
                return i;
            }
        }
        throw new ElementoNaoExisteException();
    }
}
