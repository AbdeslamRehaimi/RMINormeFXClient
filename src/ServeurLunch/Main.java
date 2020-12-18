package ServeurLunch;

import javax.naming.Context;
import javax.naming.InitialContext;
import Serveur.ServClient;
import Serveur.ServClientPerv;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import beans.Vecteur;
import java.math.BigDecimal;

public class Main extends Application {

    AnchorPane root = new AnchorPane();

    Button button;
    TextField textFieldX;
    TextField textFieldY;
    TableView tableView;
    TableColumn tableColumn;
    TableColumn tableColumn0;
    TableColumn tableColumnRes;
    Text text;
    CheckBox checkBox;
    Text text0;
    Text text1;
    ObservableList<Vecteur> oblist = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        button = new Button();
        textFieldX = new TextField();
        textFieldY = new TextField();
        tableView = new TableView();
        tableColumn = new TableColumn();
        tableColumn0 = new TableColumn();
        tableColumnRes = new TableColumn();
        text = new Text();
        checkBox = new CheckBox();
        text0 = new Text();
        text1 = new Text();

        root.setMaxHeight(USE_PREF_SIZE);
        root.setMaxWidth(USE_PREF_SIZE);
        root.setMinHeight(USE_PREF_SIZE);
        root.setMinWidth(USE_PREF_SIZE);
        root.setPrefHeight(430.0);
        root.setPrefWidth(698.0);
        root.setStyle("-fx-background-color: #99cec0;");

        button.setLayoutX(226.0);
        button.setLayoutY(94.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(25.0);
        button.setPrefWidth(79.0);
        button.setText("Calculer");

        textFieldX.setLayoutX(14.0);
        textFieldX.setLayoutY(94.0);
        textFieldX.setPrefHeight(25.0);
        textFieldX.setPrefWidth(192.0);
        textFieldX.setPromptText("Coordonnée x");

        textFieldY.setLayoutX(14.0);
        textFieldY.setLayoutY(120.0);
        textFieldY.setPrefHeight(25.0);
        textFieldY.setPrefWidth(192.0);
        textFieldY.setPromptText("Coordonnée y");

        tableView.setLayoutX(14.0);
        tableView.setLayoutY(158.0);
        tableView.setPrefHeight(234.0);
        tableView.setPrefWidth(670.0);

        tableColumn.setPrefWidth(160.0);
        tableColumn.setText("X");

        tableColumn0.setPrefWidth(160.0);
        tableColumn0.setText("Y");

        tableColumnRes.setPrefWidth(350.0);
        tableColumnRes.setText("Norme");

        text.setFill(javafx.scene.paint.Color.valueOf("#80304f"));
        text.setLayoutX(254.0);
        text.setLayoutY(43.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Norme Vecteur Calculator");
        text.setUnderline(true);
        text.setFont(new Font("Bell MT Bold", 25.0));

        checkBox.setLayoutX(14.0);
        checkBox.setLayoutY(74.0);
        checkBox.setMnemonicParsing(false);
        checkBox.setText("Ajouter au tableau");

        text0.setLayoutX(565.0);
        text0.setLayoutY(413.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("Master ISI : 2020/2021");

        text1.setLayoutX(388.0);
        text1.setLayoutY(115.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        //text1.setText("Resultat");
        text1.setFont(new Font("System Bold Italic", 17.0));

        root.getChildren().add(button);
        root.getChildren().add(textFieldX);
        root.getChildren().add(textFieldY);
        tableView.getColumns().add(tableColumn);
        tableView.getColumns().add(tableColumn0);
        tableView.getColumns().add(tableColumnRes);

        tableColumn.setCellValueFactory(new PropertyValueFactory<>("X"));
        tableColumn0.setCellValueFactory(new PropertyValueFactory<>("Y"));
        tableColumnRes.setCellValueFactory(new PropertyValueFactory<>("Norme"));

        root.getChildren().add(tableView);
        root.getChildren().add(text);
        root.getChildren().add(checkBox);
        root.getChildren().add(text0);

        root.getChildren().add(text1);

        button.setOnAction((e) -> {
            try {
                Context ctx = new InitialContext();
                ServClientPerv Conv = (ServClientPerv) ctx.lookup("rmi://localhost:1099/ServClient");
                if (!checkBox.isSelected()) {
                    double X = Double.parseDouble(textFieldX.getText());
                    double Y = Double.parseDouble(textFieldY.getText());
                    double RESULT = Conv.calculeNormeVecteur(X, Y);
                    System.out.println(String.format("la norme du vecteur est: = %.4f", RESULT));
                    //Formating the double result to get only quatre numero apres vergule.
                    text1.setText(String.format("la norme du vecteur est: = %.4f", RESULT) );

                } else {
                    double X = Double.parseDouble(textFieldX.getText());
                    double Y = Double.parseDouble(textFieldY.getText());
                    double Norme = Conv.calculeNormeVecteur(Double.parseDouble(textFieldX.getText()), Double.parseDouble(textFieldY.getText()));
                    //Formating the double result to get only quatre numero apres vergule.
                    System.out.println(String.format("la norme du vecteur est: = %.4f", Norme));
                    text1.setText(String.format("la norme du vecteur est: = %.4f", Norme) );
                    oblist.add(new Vecteur(X, Y, Norme ));
                    tableView.setItems(oblist);
                }

            } catch (Exception e1) {
                System.out.println(e1.toString());
            } finally {
                textFieldX.setText("");
                textFieldY.setText("");
                tableView.refresh();
                tableView.setItems(oblist);
            }
        });
        tableView.refresh();
        tableView.setItems(oblist);
        tableView.setItems(oblist);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Norme Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
