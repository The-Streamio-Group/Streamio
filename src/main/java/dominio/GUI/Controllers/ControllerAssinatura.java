package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Assinante;
import dominio.negocios.beans.Assinatura;
import dominio.negocios.beans.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
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
        cartaoAtual.setText(logado.getAssinatura().getNumeroCartao());
        dataAssinatura.setText(logado.getAssinatura().getDataAssinatura().toString());
        dataExpiracao.setText(logado.getAssinatura().getDataExpiracao().toString());
    }

    // botões "Renovar Assinatura" e "Cancelar Assinatura"



    public void renovarAssinatura(ActionEvent event){
        try {
            sistema.renovarAssinatura(logado.getUsuarioID());
        }
        catch(Exception e){

        }

    }

    public void cancelarAssinatura(ActionEvent event){
        try {
            sistema.cancelarAssinatura(logado.getUsuarioID());
        }
        catch (Exception e){

        }
    }

    public void sairParaMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/menuPerfis.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
