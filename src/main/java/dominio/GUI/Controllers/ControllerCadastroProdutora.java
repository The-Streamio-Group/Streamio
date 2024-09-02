package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Assinante;
import dominio.negocios.beans.Assinatura;
import dominio.negocios.beans.Produtora;
import dominio.negocios.beans.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCadastroProdutora {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nomeTF, emailTF, senhaTF;

    // implementar "Cadastrar"
    public void cadastrarProdutora(ActionEvent event) throws IOException {
        String nome = this.nomeTF.getText();
        String email = this.emailTF.getText();
        String senha = this.senhaTF.getText();

        Usuario cadastrado = new Produtora(nome,email,senha);
        try {
            sistema.cadastrarUsuario(cadastrado);
        }
        catch(Exception e){
            System.out.println("Erro!");
        }


        root = FXMLLoader.load(getClass().getResource("/Telas/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void voltar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/cadastroDecisao.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
