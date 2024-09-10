package dominio.GUI.Controllers;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Perfil;
import dominio.negocios.beans.Produtora;
import dominio.negocios.beans.TipoGenero;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

// esse é o home screen da produtora
public class ControllerProdutoraConteudos implements Initializable {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    public Produtora selecionado = (Produtora) sistema.getUsuariologado();
    public Conteudo conteudoSelecionado;

    @FXML
    private Label nomeProdutora;

    @FXML
    private TableView<Conteudo> tabelaProdutora;

    @FXML
    private TableColumn<Conteudo, String> tituloTable;

    @FXML
    private TableColumn<Conteudo, TipoGenero> generoTable;

    @FXML
    private TableColumn<Conteudo, String> idadeTable;

    @FXML
    private TableColumn<Conteudo, Duration> duracaoTable;


    private Stage stage;
    private Scene scene;
    private Parent root;


    public void voltarLogin(ActionEvent event) throws IOException {
        //Deslogar o usuário
        sistema.logoff();

        root = FXMLLoader.load(getClass().getResource("/Telas/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void adicionarConteudos(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoProdutora/produtoraAdicionarConteudo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void removerConteudo(ActionEvent event) {
        try {
            sistema.removerConteudo(conteudoSelecionado.getConteudoID());
            ObservableList<Conteudo> list = FXCollections.observableArrayList(selecionado.getProduto());
            tabelaProdutora.setItems(list);

        } catch (Exception e) {
        }

    }

    public void irRelatorio(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoProdutora/relatorioProdutora.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irPerfil(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoProdutora/editarContaProdutora.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeProdutora.setText(sistema.getUsuariologado().getNickname());
        tituloTable.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        generoTable.setCellValueFactory(new PropertyValueFactory<>("genero"));
        idadeTable.setCellValueFactory(new PropertyValueFactory<>("classificacaoIdade"));
        duracaoTable.setCellValueFactory(new PropertyValueFactory<>("duracao"));

        duracaoTable.setCellFactory(column -> new TableCell<Conteudo, Duration>() {
            @Override
            protected void updateItem(Duration duration, boolean empty) {
                super.updateItem(duration, empty);

                if (empty || duration == null) {
                    setText(null);
                } else {
                    long seconds = (long) duration.toSeconds();
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    minutes = minutes % 60;
                    seconds = seconds % 60;

                    setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                }
            }
        });

        ObservableList<Conteudo> list = FXCollections.observableArrayList(selecionado.getProduto());
        tabelaProdutora.setItems(list);


        //Selecionar Items
        tabelaProdutora.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Conteudo>() {
            @Override
            public void changed(ObservableValue<? extends Conteudo> observableValue, Conteudo oldValue, Conteudo valor) {
                conteudoSelecionado = valor;

            }
        });
    }
}
