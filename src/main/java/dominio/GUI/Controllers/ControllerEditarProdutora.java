package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerEditarProdutora {
    ISistemaFachada sistema = SistemaFachada.getInstance();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nomeTF, senhaTF;


    public void alterarNome(ActionEvent event) throws IOException {
        try {
            if(!nomeTF.getText().equals("")) {
                sistema.alterarNomeUsuario(sistema.getUsuariologado().getUsuarioID(), nomeTF.getText());

                this.voltar(event);
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo Inválido!");
            alert.setContentText("Campo Inválido, tente novamente!");
            alert.setHeaderText("AVISO!!!");
            alert.showAndWait();
        }

    }

    public void alterarSenha(ActionEvent event) throws IOException {
        try {
            if(!senhaTF.getText().equals("")) {
                sistema.alterarSenhaUsuario(sistema.getUsuariologado().getUsuarioID(), senhaTF.getText());

                this.voltar(event);
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo Inválido!");
            alert.setContentText("Campo Inválido, tente novamente!");
            alert.setHeaderText("AVISO!!!");
            alert.showAndWait();
        }

    }


    public void voltar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoProdutora/conteudosProdutora.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
