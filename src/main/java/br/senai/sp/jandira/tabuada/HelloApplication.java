package br.senai.sp.jandira.tabuada;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Definir tamanho da tela
        stage.setWidth(500);
        stage.setHeight(500);

        //Componente principal da tela
        VBox root = new VBox();
        Scene scene = new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Tabuada");
        stage.show();

        //Cabeçalho da tela
        VBox header = new VBox();
        header.setStyle("-fx-padding: 10;-fx-background-color: rgba(106,0,0,0.96)");

        //Adicionar um label ao header
        Label labelTitulo = new Label("Tabuada");
        labelTitulo.setStyle("-fx-text-fill: white;-fx-font-size: 30;-fx-font-weight: bold;");
        Label labelSubtitulo = new Label("Construa tabuadas sem limites!");
        labelSubtitulo.setStyle("-fx-text-fill: #ffe0e6;-fx-font-size: 15");

        header.getChildren().add(labelTitulo);
        header.getChildren().add(labelSubtitulo);

        //Colocar o header no root
        root.getChildren().addAll(header);

        //Criar o multiplicando
        HBox multiplicandoBox = new HBox();
         multiplicandoBox.setStyle("-fx-padding: 10;");
         Label labelMultiplicando = new Label("Multiplicando");
        TextField textFieldMultiplicando = new TextField();

        multiplicandoBox.getChildren().add(textFieldMultiplicando);
        multiplicandoBox.getChildren().add(multiplicandoBox);


        //Colocamos o multiplicandoBox no root
        root.getChildren().add(multiplicandoBox);


    }
}
