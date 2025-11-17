package br.senai.sp.jandira.tabuada;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {

    TextField textFieldMultiplicando;
    TextField textFieldMenorMultiplicador;
    TextField textFieldMaiorMultiplicador;
    ListView listaTabuada;


    @Override
    public void start(Stage stage) throws IOException {

        //Definir tamanho da tela
        //stage.setWidth(500);
        stage.setHeight(600);

        //Confirmação do encerramento Stage
        stage.setOnCloseRequest(event -> { //o nome EVENT é uma variável eu que escolho
            fechar();
            event.consume();

        });

        //Bloquear o redimensionamento da janela
        stage.setResizable(false);

        //Componente principal da tela
        VBox root = new VBox();
        Scene scene = new Scene(root);


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


        //Criar o multiplicando
         GridPane gridFormulario = new GridPane();
         gridFormulario.setPadding(new Insets(20));
         gridFormulario.setHgap(10);
         gridFormulario.setVgap(10);

         Label labelMultiplicando = new Label("Multiplicando: ");
         textFieldMultiplicando = new TextField();

         Label labelMenorMultiplicador = new Label("Menor Multiplicador: ");
         textFieldMenorMultiplicador = new TextField();

         Label labelMaiorMultiplicador = new Label("Maior Multiplicando: ");
         textFieldMaiorMultiplicador = new TextField();

         gridFormulario.add(labelMultiplicando, 0, 0);
         gridFormulario.add(textFieldMultiplicando, 1, 0);
         gridFormulario.add(labelMenorMultiplicador, 0, 1);
         gridFormulario.add(textFieldMenorMultiplicador, 1, 1);
         gridFormulario.add(labelMaiorMultiplicador, 0, 2);
         gridFormulario.add(textFieldMaiorMultiplicador, 1, 2);

         //Criar o componente de botões
        HBox boxBotoes = new HBox();
        boxBotoes.setAlignment(Pos.CENTER_RIGHT);
        boxBotoes.setPadding(new Insets(0, 20, 20, 20));
        boxBotoes.setSpacing(10);


        Button btnCalcular = new Button("Calcular");
        btnCalcular.setPrefWidth(90);
        btnCalcular.setOnAction(e -> {
            calcularTabuada();
        });


        Button btnLimpar = new Button("Limpar");
        btnLimpar.setPrefWidth(70);
        btnLimpar.setOnAction(e -> {
            limparTabuada();
        });


        Button btnSair = new Button("Sair");
        btnSair.setPrefWidth(70);
        btnSair.setOnAction(e -> {
            fechar();

        });

        //Adicionar os botões na boxBotões
        boxBotoes.getChildren().addAll(btnCalcular, btnLimpar, btnSair);

        //Adicionar componente ListView
        VBox boxResultado = new VBox();
        boxResultado.setPadding(new Insets(0, 20, 20, 20));
        Label labelResultado = new Label("Resultado: ");
        labelResultado.setStyle("-fx-text-fill: rgba(106,0,0,0.96);-fx-font-size: 18; -fx-font-weight: bold");

        //Adicionar o ListView
        listaTabuada = new ListView();


        //Adicionar o lebel ao boxResultado
        boxResultado.getChildren().add(labelResultado);
        boxResultado.getChildren().add(listaTabuada);

        //Adicionar componentes ao root
        root.getChildren().add(header);
        root.getChildren().add(gridFormulario);
        root.getChildren().add(boxBotoes);
        root.getChildren().add(boxResultado);

        stage.setScene(scene);
        stage.setTitle("Tabuada");
        stage.show();


    }
    public void calcularTabuada() {

        int multiplicando = Integer.parseInt(textFieldMultiplicando.getText());
        int menorMultiplicador = Integer.parseInt(textFieldMenorMultiplicador.getText());
        int maiorMultiplicador = Integer.parseInt(textFieldMaiorMultiplicador.getText());

        if(menorMultiplicador > maiorMultiplicador) {
            int auxiliar = menorMultiplicador;
            menorMultiplicador = maiorMultiplicador;
            maiorMultiplicador = auxiliar;
        }

        int tamanho = maiorMultiplicador - menorMultiplicador + 1;
        String[] tabuada = new String[tamanho];

        int contador = 0;
        while(contador < tamanho){
            double produto = multiplicando * menorMultiplicador;
            tabuada[contador] = multiplicando + "x" + menorMultiplicador + "=" + produto;
            contador++;
            menorMultiplicador++;
        }

        listaTabuada.getItems().clear();
        listaTabuada.getItems().addAll(tabuada);

    }

    public void limparTabuada() {
        listaTabuada.getItems().clear();
        textFieldMultiplicando.setText("");
        textFieldMenorMultiplicador.setText("");
        textFieldMaiorMultiplicador.setText("");
        textFieldMultiplicando.requestFocus();
    }

    public void fechar() {
        Alert alertaFechar = new Alert(Alert.AlertType.CONFIRMATION,
                "Confirma a saída do sistema? ", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> resposta = alertaFechar.showAndWait();
        if (resposta.isPresent() && resposta.get() == ButtonType.YES)
            Platform.exit();


    }

}
