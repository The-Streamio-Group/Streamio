package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Avaliacao;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Produtora;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerRelatorio implements Initializable {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;
    public Produtora selecionado = (Produtora) sistema.getUsuariologado();

    @FXML
    private TableView<Conteudo> tabelaRelatorio;

    @FXML
    private TableColumn<Conteudo, String> tituloTable;

    @FXML
    private TableColumn<Conteudo, Integer> avaliacaoTable;

    @FXML
    private TableColumn<Conteudo, Integer> visuTable;

    @FXML
    private TableColumn<Conteudo, Float> notaTable;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tituloTable.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        avaliacaoTable.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getAvaliacoes().size())
        );
        visuTable.setCellValueFactory(new PropertyValueFactory<>("numeroViews"));
        notaTable.setCellValueFactory(new PropertyValueFactory<>("notaGeral"));

        ObservableList<Conteudo> list = FXCollections.observableArrayList(selecionado.getProduto());
        tabelaRelatorio.setItems(list);

    }

    public void voltarMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoProdutora/conteudosProdutora.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
