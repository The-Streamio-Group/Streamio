package dominio.GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerPerfilHomeScreen {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // implemetar o logout



    public void pesquisarConteudo(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/perfilPesquisa.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // é para mover para a tela de menuPerfis
    public void irMenuPerfis(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/menuPerfis.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
