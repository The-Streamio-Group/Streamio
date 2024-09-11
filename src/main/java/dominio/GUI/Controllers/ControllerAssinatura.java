package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Assinante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerAssinatura implements Initializable {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Assinante logado = (Assinante) sistema.getUsuariologado();

    @FXML
    private Label cartaoAtual;

    @FXML
    private Label dataAssinatura;

    @FXML
    private Label dataExpiracao;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateTimeFormatter formatacaoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        cartaoAtual.setText(logado.getAssinatura().getNumeroCartao());

        String dataAs = logado.getAssinatura().getDataAssinatura().format(formatacaoData);
        dataAssinatura.setText(dataAs);

        String dataEx = logado.getAssinatura().getDataExpiracao().format(formatacaoData);
        dataExpiracao.setText(dataEx);
    }

    // botões "Renovar Assinatura" e "Cancelar Assinatura"


    public void renovarAssinatura(ActionEvent event) {
        try {
            sistema.renovarAssinatura(logado.getUsuarioID());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Assinatura em andamento!");
            alert.setContentText("Sua assinatura ainda está em andamento, aproveite!");
            alert.setHeaderText("Erro");
            alert.showAndWait();
        }

    }

    public void cancelarAssinatura(ActionEvent event) {
        try {
            sistema.cancelarAssinatura(logado.getUsuarioID());

            //Deslogar
            sistema.logoff();
            root = FXMLLoader.load(getClass().getResource("/Telas/login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {

        }
    }

    public void sairParaMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/menuPerfis.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
