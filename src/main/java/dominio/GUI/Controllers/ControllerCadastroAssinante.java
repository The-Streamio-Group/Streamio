package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Assinante;
import dominio.negocios.beans.Assinatura;
import dominio.negocios.beans.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCadastroAssinante {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    // implementar "Cadastrar"
    @FXML
    private TextField nomeTF, emailTF, senhaTF, ncTF;

    public void cadastrarAssinante(ActionEvent event) throws IOException {
        try {
            if (!nomeTF.getText().isEmpty() && !emailTF.getText().isEmpty() &&
                    !senhaTF.getText().isEmpty() && !ncTF.getText().isEmpty()) {
                String nome = this.nomeTF.getText();
                String email = this.emailTF.getText();
                String senha = this.senhaTF.getText();
                String cartao = this.ncTF.getText();
                Usuario cadastrado = new Assinante(nome, email, senha, new Assinatura());
                sistema.cadastrarUsuario(cadastrado);
                sistema.realizarAssinatura(cadastrado.getUsuarioID(), cartao);

                root = FXMLLoader.load(getClass().getResource("/Telas/login.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!");
            alert.setContentText("Erro: Preencha todos os campos");
            alert.setHeaderText("ERRO!!!");
            alert.showAndWait();
        }



    }

    public void voltar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/cadastroDecisao.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
