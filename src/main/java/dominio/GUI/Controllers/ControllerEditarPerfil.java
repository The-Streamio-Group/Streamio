package dominio.GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerEditarPerfil {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // botões "Alterar" para Nickname e Idade
    // checkbox "Assistir apenas conteúdos infantis?"
    // botão "Deletar Perfil"

    public void sair(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/menuPerfis.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
