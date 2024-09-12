package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Perfil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPesquisa implements Initializable {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Conteudo conteudoSelecionado;

    // implementar "Pesquisar"
    @FXML
    private TextField pesquisaTF;

    @FXML
    private ListView<Conteudo> tabelaConteudo;

    @FXML
    private Label resultados;

    @FXML
    private Button assistirBT;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabelaConteudo.setOpacity(1);
        resultados.setOpacity(0);
        assistirBT.setOpacity(0);

        tabelaConteudo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Conteudo>() {
            @Override
            public void changed(ObservableValue<? extends Conteudo> observableValue, Conteudo oldValue, Conteudo valor) {
                conteudoSelecionado = valor;

            }
        });
    }

    public void assistir(ActionEvent event) throws IOException {
        try {
            sistema.conteudoSelecionado(conteudoSelecionado.getConteudoID());

        } catch (Exception e) {

        }
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/conteudoDetalhado.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void procurar(ActionEvent event) {
        String resultadoPesquisa = pesquisaTF.getText();
        try {
            tabelaConteudo.getItems().clear();
            tabelaConteudo.getItems().addAll(sistema.procurarConteudoPorTitulo(resultadoPesquisa));
            tabelaConteudo.setOpacity(1);
            if (!tabelaConteudo.getItems().isEmpty()) {
                resultados.setOpacity(1);
                resultados.setText("RESULTADOS ENCONTRADOS");
                resultados.setStyle("-fx-text-fill: #27fa6f");
                assistirBT.setOpacity(1);
            }


        } catch (Exception e) {
            resultados.setOpacity(1);
            resultados.setText("Conteúdos não encontrados, pesquise outro título catalogado");
            resultados.setStyle("-fx-text-fill: red");
        }
    }

    public void voltar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/perfilHomeScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
