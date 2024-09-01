package dominio.GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class ControllerLogin {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField senha;

    @FXML
    private Button logarBT;

    // implementar "Entrar" analisando os fields e o tipo de usuário

    public void irCadastro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/cadastroDecisao.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
