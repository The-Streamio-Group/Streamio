package dominio.negocios;

import dominio.dados.RepositorioReproducaoConteudoList;
import dominio.dados.interfaces.IRepositorioReproducaoConteudo;
import dominio.exceptions.*;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Perfil;
import dominio.negocios.beans.ReproducaoConteudo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ControllerReproducaoConteudo {
    private static ControllerReproducaoConteudo instancia;
    private final IRepositorioReproducaoConteudo repositorio;

    private ControllerReproducaoConteudo() {
        this.repositorio = RepositorioReproducaoConteudoList.getInstance();
    }

    public static ControllerReproducaoConteudo getInstance() {
        if (instancia == null) {
            instancia = new ControllerReproducaoConteudo();
        }
        return instancia;
    }

    public void cadastrarReprodutoraConteudo(ReproducaoConteudo r) throws ElementoJaExisteException, ElementoNullException {
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

    public ReproducaoConteudo cadastrarReprodutoraConteudo(Conteudo c, long minutos, Perfil p) throws ElementoJaExisteException, ElementoNullException {
        ReproducaoConteudo r = new ReproducaoConteudo(c, minutos, p);

        if (!existeReprodutoraConteudo(r.getReprodutoraConteudoID())) {

            this.cadastrarReprodutoraConteudo(r);
        } else {
            throw new ElementoJaExisteException();
        }
        return r;

    }


    public ReproducaoConteudo procurarReprodutoraConteudo(UUID id) throws ElementoNaoExisteException {
        return this.repositorio.procurar(id);
    }

    public List<ReproducaoConteudo> filtrarDono(Perfil dono) {
        return this.repositorio.procurarDono(dono);
    }

    public void removerReprodutoraConteudo(UUID id) throws ElementoNaoExisteException {
        ReproducaoConteudo removido = procurarReprodutoraConteudo(id);

        if (removido != null) {
            this.repositorio.remover(id);
        }

    }

    //UPDATE
    public void atualizarReprodutoraConteudo(UUID antigoid, ReproducaoConteudo novo) throws MesmoElementoException, ElementoNullException, ElementoJaExisteException, ElementoNaoExisteException {
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
        ReproducaoConteudo data = procurarReprodutoraConteudo(id);
        if (!novaData.equals(data.getDataAssistido())) {
            data.setDataAssistido(novaData);
        } else {
            throw new MesmoElementoException();
        }
    }

    public void atualizarTempoAssistido(UUID id, Duration novoTempo) throws ElementoNaoExisteException, MesmoElementoException {
        ReproducaoConteudo tempo = procurarReprodutoraConteudo(id);
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
