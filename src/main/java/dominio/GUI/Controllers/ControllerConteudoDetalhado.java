package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Conteudo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerConteudoDetalhado implements Initializable {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Conteudo conteudoEspecifico = sistema.getConteudoSelecionado();

    @FXML
    private Label nomeConteudo, sinopse, duracao, idade;

    public void irPerfilHomescreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/perfilHomeScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irReproducao(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/reproducaoConteudo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irAvaliacao(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/avaliarConteudo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void favoritar(ActionEvent event) throws IOException {
        try {
            sistema.adicionarFavorito(conteudoEspecifico);
        } catch (Exception e) {
        }
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/perfilHomeScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeConteudo.setText(conteudoEspecifico.getTitulo());
        sinopse.setText(conteudoEspecifico.getDescricao());
        duracao.setText(conteudoEspecifico.getDuracao().toString());
        idade.setText(conteudoEspecifico.getClassificacaoIdade());

    }

}
