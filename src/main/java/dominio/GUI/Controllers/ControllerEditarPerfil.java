package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Perfil;
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

public class ControllerEditarPerfil {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nicknameTF, idadeTF;


    // botões "Alterar" para Nickname e Idade
    // checkbox "Assistir apenas conteúdos infantis?"
    // botão "Deletar Perfil"


    public void checarInput(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            event.consume();

            idadeTF.setStyle("-fx-border-color: red");
        } else {
            idadeTF.setStyle("-fx-border-color: blue");
        }

    }

    public void alterarNickname(ActionEvent event) throws IOException {
        try {
            if(!nicknameTF.getText().equals("")) {
                sistema.mudarNomePerfil(sistema.getPerfilLogado().getPerfilID(), nicknameTF.getText());
                this.sair(event);
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


        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/menuPerfis.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void alterarIdade(ActionEvent event) throws IOException {
        try {
            if(!idadeTF.getText().equals("")){
                sistema.mudarFaixaEtaria(sistema.getPerfilLogado().getPerfilID(), Integer.parseInt(idadeTF.getText()));
                this.sair(event);
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

    public void deletarPerfil(ActionEvent event) throws IOException  {
        try {
            sistema.removerPerfil(sistema.getPerfilLogado().getPerfilID());
            root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/menuPerfis.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
    }

    public void sair(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/menuPerfis.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
