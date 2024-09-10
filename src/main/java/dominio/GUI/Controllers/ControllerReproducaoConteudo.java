package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.ReproducaoConteudo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerReproducaoConteudo implements Initializable {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private ReproducaoConteudo rep;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private MediaView mediaView;

    @FXML
    private Button playButton, pauseButton, resetButton;

    @FXML
    private Label tituloConteudo;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;



    // ir para o conteudo detalhado atual
    public void irConteudoDetalhado(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/conteudoDetalhado.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        file = sistema.getConteudoSelecionado().getPath();
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        tituloConteudo.setText(sistema.getConteudoSelecionado().getTitulo());

        //Adicionar no histórico
        try {
            rep = new ReproducaoConteudo(sistema.getConteudoSelecionado(), sistema.getConteudoSelecionado().getDuracao().toMinutes(),sistema.getPerfilLogado());
            sistema.assistirConteudo(rep);
            sistema.reproducaoMomento(rep);
        } catch(Exception e){

        }

    }

    public void playMedia() {
        mediaPlayer.play();

    }

    public void pauseMedia() {
        mediaPlayer.pause();
    }

    public void resetMedia() {
        if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }
    }
}
