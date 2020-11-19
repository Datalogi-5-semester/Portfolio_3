// Benjamin Andersen, Marie Riskær, Mikkel Iuel, Nanna Holst Larsen

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    //static String url = "jdbc:sqlite:C:\\Benjih\\RUC\\5 semester\\Portfolio3_database";

    @Override
    public void start(Stage primaryStage) throws Exception{

        DataConnection dataConnection;

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
