package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Perfil;
import dominio.negocios.beans.ReproducaoConteudo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPerfilHomeScreen implements Initializable {
    ISistemaFachada sistema = SistemaFachada.getInstance();

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Conteudo conteudoSelecionado;
    private ReproducaoConteudo reproducaoConteudo;

    @FXML
    private Label nomePerfil;

    @FXML
    private ListView<Conteudo> favoritos;

    @FXML
    private ListView<ReproducaoConteudo> historico;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomePerfil.setText(sistema.getPerfilLogado().getNick());



        ObservableList<Conteudo> listFavoritos = FXCollections.observableArrayList(sistema.getPerfilLogado().getConteudosFavoritos());
        ObservableList<ReproducaoConteudo> listHistorico = FXCollections.observableArrayList(sistema.filtrarHistorico(sistema.getPerfilLogado()));
        favoritos.setItems(listFavoritos);
        historico.setItems(listHistorico);

        favoritos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Conteudo>() {
            @Override
            public void changed(ObservableValue<? extends Conteudo> observableValue, Conteudo oldValue, Conteudo valor) {
                conteudoSelecionado = valor;

            }
        });

        historico.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ReproducaoConteudo>() {
            @Override
            public void changed(ObservableValue<? extends ReproducaoConteudo> observableValue, ReproducaoConteudo oldValue, ReproducaoConteudo valor) {
                reproducaoConteudo = valor;

            }
        });
    }

    public void pesquisarConteudo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/perfilPesquisa.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void removerF(ActionEvent event){
        try {
            sistema.removerFavorito(conteudoSelecionado);
            ObservableList<Conteudo> list = FXCollections.observableArrayList(sistema.getPerfilLogado().getConteudosFavoritos());
            favoritos.setItems(list);
        }
        catch (Exception e){}
    }

    public void removerH(ActionEvent event){
        try {
            sistema.removerReproducaoConteudo(reproducaoConteudo.getReprodutoraConteudoID());
            ObservableList<ReproducaoConteudo> listHistorico = FXCollections.observableArrayList(sistema.filtrarHistorico(sistema.getPerfilLogado()));
            historico.setItems(listHistorico);

        }
        catch (Exception e){}
    }

    public void assistir(ActionEvent event) throws IOException {
        try {
            sistema.conteudoSelecionado(conteudoSelecionado.getConteudoID());

        } catch (Exception e) {

        }
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/conteudoDetalhado.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void assistirH(ActionEvent event) throws IOException {
        try {
            ReproducaoConteudo alternada = sistema.procurarReproducaoConteudo(reproducaoConteudo.getReprodutoraConteudoID());
            conteudoSelecionado = alternada.getConteudo();
        } catch (Exception e) {

        }
        this.assistir(event);
    }

    //É para mover para a tela de menuPerfis
    public void irMenuPerfis(ActionEvent event) throws IOException {

        //Deslogar do perfil
        sistema.logoffPerfil();

        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/menuPerfis.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
