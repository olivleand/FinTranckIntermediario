package app;

import DatAcessObject.TransactionDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class FinApp extends Application {

    @Override
    public void start(Stage stage)
            throws Exception {

        Parent root =
                FXMLLoader.load(
                        getClass()
                                .getResource(
                                        "/view/main.fxml"));

        Scene scene =
                new Scene(root, 900, 650);

        // CSS
        scene.getStylesheets().add(
                getClass()
                        .getResource(
                                "/view/style.css")
                        .toExternalForm());

        TransactionDAO dao =
                new TransactionDAO();

        dao.criarTabela();

        stage.setTitle(
                "FinTrack");

        stage.setScene(
                scene);

        stage.setTitle("FinTrack");

        stage.setResizable(false);

        stage.centerOnScreen();

        stage.setScene(scene);

        stage.show();

        stage.show();

    }

}