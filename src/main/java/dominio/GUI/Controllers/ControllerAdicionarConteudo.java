package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.TipoGenero;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdicionarConteudo implements Initializable {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ComboBox<TipoGenero> comboBoxG;

    @FXML
    private TextField tituloFT, duracaoFT;

    @FXML
    private TextArea descricaoFT;

    public void checarInput(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            event.consume();

            duracaoFT.setStyle("-fx-border-color: red");
        } else {
            duracaoFT.setStyle("-fx-border-color: blue");
        }

    }

    public void adicionarConteudo(ActionEvent event) {
        String titulo = tituloFT.getText();
        String descricao = descricaoFT.getText();
        long duracao = Long.parseLong(duracaoFT.getText());
        String cB = comboBox.getSelectionModel().getSelectedItem();
        TipoGenero genero = comboBoxG.getSelectionModel().getSelectedItem();
        try {
            Conteudo novo = new Conteudo(titulo, descricao, genero, cB, duracao);
            sistema.adicionarConteudo(novo);
            voltar(event);
        } catch (Exception e) {
        }


    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoProdutora/conteudosProdutora.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(FXCollections.observableArrayList("10-12 Anos", "14 - 17 Anos", "+18 anos"));
        comboBoxG.setItems(FXCollections.observableArrayList(TipoGenero.values()));

    }
}
