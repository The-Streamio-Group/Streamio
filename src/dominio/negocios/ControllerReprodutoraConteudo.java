package dominio.negocios;

import dominio.dados.RepositorioReproducaoConteudoList;
import dominio.dados.interfaces.IRepositorioConteudo;
import dominio.dados.interfaces.IRepositorioReprodutoraConteudo;
import dominio.exceptions.*;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.ReprodutoraConteudo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

public class ControllerReprodutoraConteudo {
    private static ControllerReprodutoraConteudo instancia;
    private final IRepositorioReprodutoraConteudo repositorio;

    private ControllerReprodutoraConteudo() {
        this.repositorio = RepositorioReproducaoConteudoList.getInstance();
    }

    public static ControllerReprodutoraConteudo getInstance() {
        if (instancia == null) {
            instancia = new ControllerReprodutoraConteudo();
        }
        return instancia;
    }

    public void cadastrarReprodutoraConteudo(ReprodutoraConteudo r) throws ElementoJaExisteException, ElementoNullException {
        if (r != null) {
            if (!existeReprodutoraConteudo(r.getReprodutoraConteudoID())) {

                this.repositorio.cadastrar(r);
            } else {
                throw new ElementoJaExisteException();
            }

        } else {
            throw new ElementoNullException();
        }
    }

    public void cadastrarReprodutoraConteudo(Conteudo c, long minutos) throws ElementoJaExisteException, ElementoNullException {
        ReprodutoraConteudo r = new ReprodutoraConteudo(c, minutos);

        if (!existeReprodutoraConteudo(r.getReprodutoraConteudoID())) {

            this.repositorio.cadastrar(r);
        } else {
            throw new ElementoJaExisteException();
        }

    }


    public ReprodutoraConteudo procurarReprodutoraConteudo(UUID id) throws ElementoNaoExisteException {
        return this.repositorio.procurar(id);
    }

    public void removerReprodutoraConteudo(UUID id) throws ElementoNaoExisteException {
        ReprodutoraConteudo removido = procurarReprodutoraConteudo(id);

        if (removido != null) {
            this.repositorio.remover(id);
        }
    }

    //UPDATE
    public void atualizarReprodutoraConteudo(UUID antigoid, ReprodutoraConteudo novo) throws MesmoElementoException, ElementoNullException, ElementoJaExisteException, ElementoNaoExisteException {
        if (procurarReprodutoraConteudo(antigoid).equals(novo)) {
            if (!existeReprodutoraConteudo(novo.getReprodutoraConteudoID())) {
                repositorio.atualizar(antigoid, novo);
            } else {
                throw new ElementoJaExisteException();
            }
        } else {
            throw new MesmoElementoException();
        }
    }

    public void atualizarDataAssistido(UUID id, LocalDate novaData) throws ElementoNaoExisteException, MesmoElementoException {
        ReprodutoraConteudo data = procurarReprodutoraConteudo(id);
        if (!novaData.equals(data.getDataAssistido())) {
            data.setDataAssistido(novaData);
        } else {
            throw new MesmoElementoException();
        }
    }

    public void atualizarTempoAssistido(UUID id, Duration novoTempo) throws ElementoNaoExisteException, MesmoElementoException {
        ReprodutoraConteudo tempo = procurarReprodutoraConteudo(id);
        if (!novoTempo.equals(tempo.getTempoAssistido())) {
            tempo.setTempoAssistido(novoTempo);
        } else {
            throw new MesmoElementoException();
        }
    }

    //READ
    public boolean existeReprodutoraConteudo(UUID id) {
        return this.repositorio.existe(id);
    }
}
