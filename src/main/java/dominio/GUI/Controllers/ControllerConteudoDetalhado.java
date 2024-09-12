package dominio.GUI.Controllers;

import dominio.exceptions.JaFavoritoException;
import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Conteudo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

public class ControllerConteudoDetalhado implements Initializable {
    ISistemaFachada sistema = SistemaFachada.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Conteudo conteudoEspecifico = sistema.getConteudoSelecionado();
    private String titulo = sistema.getConteudoSelecionado().getTitulo();


    @FXML
    private Label nomeConteudo, sinopse, duracao, idade;

    @FXML
    private ImageView posterImageView;

    public void irPerfilHomescreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/perfilHomeScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irReproducao(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/reproducaoConteudo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void irAvaliacao(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/avaliarConteudo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void favoritar(ActionEvent event) throws IOException {
        try {
            sistema.adicionarFavorito(conteudoEspecifico);
        } catch (JaFavoritoException e1) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!");
            alert.setContentText("Esse conteúdo já está favoritado!");
            alert.setHeaderText("ERRO!!!");
            alert.showAndWait();

        } catch (Exception e) {
            /* Exception Silenciosa */
        }
        root = FXMLLoader.load(getClass().getResource("/Telas/FluxoAssinante/perfilHomeScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeConteudo.setText(conteudoEspecifico.getTitulo());
        sinopse.setText(conteudoEspecifico.getDescricao());
        titulo = titulo.replace(" ","%");
        this.carregarPoster(titulo);

        //Formatar a duração
        Duration duration = conteudoEspecifico.getDuracao();
        if (duration != null) {
            long seconds = (long) duration.toSeconds();
            long minutes = seconds / 60;
            long hours = minutes / 60;
            minutes = minutes % 60;
            seconds = seconds % 60;

            //Horas:Minutos:Segundos
            duracao.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        } else {
            duracao.setText("Duração não disponível");
        }
        idade.setText(conteudoEspecifico.getClassificacaoIdade());

    }

        public void carregarPoster (String titulo){
            try {
                // Constrói a URL da API
                URL url2 = new URL("https://api.themoviedb.org/3/search/movie?query=" + titulo + "&language=pt-BR");
                HttpURLConnection com = (HttpURLConnection) url2.openConnection();
                com.setRequestMethod("GET");
                com.setRequestProperty("accept", "application/json");
                com.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkOWRkYWJkYmIxYzE5ZjU0NzA4M2I3NGRiODJiNWFhMSIsIm5iZiI6MTcyNjEwOTk3NS4xMzM0ODQsInN1YiI6IjY2ZTI1ODY0MDAwMDAwMDAwMDk1MTE5NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.InyXlbTOjNoABqWzPMKRUyLikE7cuHERn2ktWgYEU28");

                // Lê a resposta da API
                BufferedReader bf = new BufferedReader(new InputStreamReader(com.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bf.readLine()) != null) {
                    response.append(line);
                }
                bf.close();

                // Faz o parsing da resposta JSON
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());

                // Extrai o array de resultados
                JSONArray results = (JSONArray) jsonObject.get("results");

                if (!results.isEmpty()) {
                    // Pega o primeiro filme
                    JSONObject filme = (JSONObject) results.getFirst();
                    String posterPath = (String) filme.get("poster_path");

                    // Constrói a URL completa do pôster
                    String fullPosterPath = "https://image.tmdb.org/t/p/w500" + posterPath;

                    // Carrega a imagem no ImageView
                    Image image = new Image(fullPosterPath);
                    posterImageView.setImage(image); // Define a imagem no ImageView
                } else {
                    System.out.println("Nenhum resultado encontrado.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

