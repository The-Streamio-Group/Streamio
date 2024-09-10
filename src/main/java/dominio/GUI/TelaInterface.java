package dominio.GUI;

import dominio.negocios.ISistemaFachada;
import dominio.negocios.SistemaFachada;
import dominio.negocios.beans.Assinante;
import dominio.negocios.beans.Assinatura;
import dominio.negocios.beans.Produtora;
import dominio.negocios.beans.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class TelaInterface extends Application {
    ISistemaFachada sistemaFachada = SistemaFachada.getInstance();
    Assinatura assinaturaTeste2 = new Assinatura("cartãofoda");
    Usuario teste = new Produtora("teste","email@teste","123");
    Usuario teste2 = new Assinante("teste","email@assinante","123", assinaturaTeste2);
    @Override
    public void start(Stage stage) throws IOException {
        try {
            sistemaFachada.cadastrarUsuario(teste);
            sistemaFachada.cadastrarUsuario(teste2);
            sistemaFachada.realizarAssinatura(teste2.getUsuarioID(),"cartãofoda");
            assinaturaTeste2.setDataExpiracao(LocalDate.of(2024,9,8));
            FXMLLoader fxmlLoader = new FXMLLoader(TelaInterface.class.getResource("/Telas/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            stage.setTitle("Streamio Project");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println("Erro na main!");
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}
