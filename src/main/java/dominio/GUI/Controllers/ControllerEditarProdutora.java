package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            System.out.println(nomeTF.getText());
            sistema.alterarNomeUsuario(sistema.getUsuariologado().getUsuarioID(), nomeTF.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }


        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoProdutora/conteudosProdutora.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void alterarSenha(ActionEvent event) throws IOException {
        try {
            sistema.alterarSenhaUsuario(sistema.getUsuariologado().getUsuarioID(), senhaTF.getText());
        } catch (Exception e) {
        }

        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoProdutora/conteudosProdutora.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void voltar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoProdutora/conteudosProdutora.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
