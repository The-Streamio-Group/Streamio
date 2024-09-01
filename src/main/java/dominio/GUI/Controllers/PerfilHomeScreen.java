package dominio.GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PerfilHomeScreen {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void editarConta(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/editarContaAssinante.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void pesquisarConteudo(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/perfilPesquisa.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void deslogar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
