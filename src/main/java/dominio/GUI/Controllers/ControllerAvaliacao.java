package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Avaliacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAvaliacao {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Slider sliderAvaliacao;

    public void avaliar(ActionEvent event) throws IOException {
        int valor = (int) sliderAvaliacao.getValue();

        try {
            sistema.realizarAvaliacao(new Avaliacao(valor, sistema.getPerfilLogado()), sistema.getReproducao().getReprodutoraConteudoID());
            this.sair(event);

        } catch (Exception e) {
        }
    }

    public void sair(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/conteudoDetalhado.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
