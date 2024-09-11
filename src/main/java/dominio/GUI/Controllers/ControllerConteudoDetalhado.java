package dominio.GUI.Controllers;

import dominio.exceptions.JaFavoritoException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
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
        } catch (JaFavoritoException e1) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!");
            alert.setContentText("Esse conteúdo já está favoritado!");
            alert.setHeaderText("ERRO!!!");
            alert.showAndWait();

        } catch (Exception e) {
            /* Exception Silenciosa */
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

        //Formatar a duração
        Duration duration = conteudoEspecifico.getDuracao();
        if (duration != null) {
            long seconds = (long) duration.toSeconds();
            long minutes = seconds / 60;
            long hours = minutes / 60;
            minutes = minutes % 60;
            seconds = seconds % 60;

            //Horas:Minutos:Segundos
            duracao.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        } else {
            duracao.setText("Duração não disponível");
        }
        idade.setText(conteudoEspecifico.getClassificacaoIdade());

    }

}
