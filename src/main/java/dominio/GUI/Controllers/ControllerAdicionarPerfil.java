package dominio.GUI.Controllers;


import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Perfil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdicionarPerfil {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField nicknameTF, idadeTF;


    public void checarInput(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            event.consume();

            idadeTF.setStyle("-fx-border-color: red");
        } else {
            idadeTF.setStyle("-fx-border-color: blue");
        }

    }

    public void adicionarPerfil(ActionEvent event) throws IOException {
        String nickname = this.nicknameTF.getText();
        int idade = Integer.parseInt(this.idadeTF.getText());

        Perfil adicionado = new Perfil(nickname, idade);
        try {
            sistema.cadastrarPerfil(adicionado);
        } catch (Exception e) {
            System.out.println("Erro!");
        }

        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/menuPerfis.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irMenuPerfis(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/menuPerfis.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
