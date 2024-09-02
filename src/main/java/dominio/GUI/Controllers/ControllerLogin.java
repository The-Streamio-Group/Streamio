package dominio.GUI.Controllers;

import dominio.exceptions.UsuarioInexistenteException;
import dominio.exceptions.UsuarioJaLogadoException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class ControllerLogin {
    ISistemaFachada sistema = SistemaFachada.getInstance();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField senhaTF;

    @FXML
    private Button logarBT;

    // implementar "Entrar" analisando os fields e o tipo de usuário

    public void handleEntrar(ActionEvent event) {

        String email = this.emailTF.getText();
        String senha = this.senhaTF.getText();

        try {
            this.sistema.realizarLogin(email, senha);
            if (this.sistema.getUsuariologado() instanceof Produtora) {
                this.abrirTelaProdutora(event);
            }
            if (this.sistema.getUsuariologado() instanceof Assinante) {
                this.abrirTelaAssinante(event);
            }
        } catch (Exception e) {
            System.out.println("Erro no login!");
        }
    }

    public void irCadastro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/cadastroDecisao.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void abrirTelaAssinante(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/menuPerfis.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void abrirTelaProdutora(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoProdutora/conteudosProdutora.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
