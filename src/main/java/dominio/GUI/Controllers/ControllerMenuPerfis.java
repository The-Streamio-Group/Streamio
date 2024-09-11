package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Assinante;
import dominio.negocios.beans.Perfil;
import dominio.negocios.beans.Produtora;
import dominio.negocios.beans.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenuPerfis implements Initializable {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    Usuario logado = sistema.getUsuariologado();
    Assinante logado2 = (Assinante) logado;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ListView<Perfil> listaPerfil;
    public Perfil selecionado;

    // adicionar os botões que precisam da seleção de um perfil (entrar, editar)
    // "Entrar" com perfil selecionado direciona para PerfilHomeScreen
    // implementar sistema de Logout em irLogoutAssinante



    public void entrarPerfil(ActionEvent event) throws IOException {
        try {
            if(selecionado != null) {
                sistema.trocarPerfil(selecionado.getNick());

                root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/PerfilHomeScreen.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selecione um perfil!");
            alert.setContentText("Selecione um perfil para fazer essa etapa!");
            alert.setHeaderText("AVISO!!!");
            alert.showAndWait();
        }


    }

    public void editarPerfil(ActionEvent event) throws IOException {
        try {
            if(selecionado != null) {
                sistema.trocarPerfil(selecionado.getNick());

                root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/editarPerfil.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selecione um perfil!");
            alert.setContentText("Selecione um perfil para fazer essa etapa!");
            alert.setHeaderText("AVISO!!!");
            alert.showAndWait();

        }




    }

    public void irEditarConta(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/editarContaAssinante.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irAssinatura(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/assinatura.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irAdicionarPerfil(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/adicionarPerfil.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irLogoutAssinante(ActionEvent event) throws IOException {
        //Deslogar o usuário
        sistema.logoff();

        root = FXMLLoader.load(getClass().getResource("/Telas/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaPerfil.getItems().addAll(logado2.getPerfis());

        listaPerfil.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Perfil>() {
            @Override
            public void changed(ObservableValue<? extends Perfil> observableValue, Perfil oldValue, Perfil valor) {
                selecionado = valor;

            }
        });

    }
}
