package dominio.GUI.Controllers;

import dominio.exceptions.*;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Optional;

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

    public void handleEntrar(ActionEvent event) throws IOException {

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
        } catch (AssinaturaExpiradaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Assinatura Expirada!");

            //Botôes do Pop-up de erro
            ButtonType refazerAssinaturaBT = new ButtonType("Refazer Assinatura");
            ButtonType sair = new ButtonType("Sair");

            //Alerta
            alert.getButtonTypes().setAll(refazerAssinaturaBT, sair);
            alert.setContentText("Refaça a sua assinatura, pois ela expirou seu bobão");
            alert.setHeaderText("Erro");

            //Pegar o valor do que o usuário escolher
            Optional<ButtonType> resultado = alert.showAndWait();


            if (resultado.isPresent() && resultado.get() == refazerAssinaturaBT) {
                try {
                    //Aqui renovamos a assinatura
                    Assinante renovar = (Assinante) sistema.procurarPorEmail(email);
                    sistema.renovarAssinatura(renovar.getUsuarioID());

                } catch (Exception e1) {
                    System.out.println("errinho!");
                }


            }


        } catch (SenhaErradaException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Senha Incorreta!");
            alert.setContentText("Senha Incorreta, tente novamente!");
            alert.setHeaderText("AVISO!!!");
            alert.showAndWait();

        } catch (ElementoNaoExisteException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Usuário Não existe!");
            alert.setContentText("Erro: Usuário Não Existe");
            alert.setHeaderText("ERRO!!!");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!");
            alert.setContentText("Erro desconhecido!");
            alert.setHeaderText("ERRO!!!");
            alert.showAndWait();

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
