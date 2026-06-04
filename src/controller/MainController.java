package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Transaction;
import DatAcessObject.TransactionDAO;


import java.time.LocalDate;

public class MainController {

    @FXML
    private Label tituloLabel;

    @FXML
    private TableView<Transaction> tabelaTransacoes;

    @FXML
    private TableColumn<Transaction, LocalDate> colData;

    @FXML
    private TableColumn<Transaction, String> colDescricao;

    @FXML
    private TableColumn<Transaction, Double> colValor;

    @FXML
    private TableColumn<Transaction, String> colTipo;

    private ObservableList<Transaction> lista =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao")
        );

        colValor.setCellValueFactory(
                new PropertyValueFactory<>("valor")
        );

        colData.setCellValueFactory(
                new PropertyValueFactory<>("data")
        );

        TransactionDAO dao =
                new TransactionDAO();

        lista.addAll(
                dao.listar());

        tabelaTransacoes.setItems(lista);

    }

    @FXML
    public void adicionarTransacao() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/view/cadastro.fxml"));

            Scene scene =
                    new Scene(
                            loader.load());

            Stage stage =
                    new Stage();

            stage.setTitle(
                    "Nova Transação");

            stage.setScene(
                    scene);

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    public void removerTransacao(){

        Transaction selecionada =
                tabelaTransacoes
                        .getSelectionModel()
                        .getSelectedItem();

        if(selecionada != null){

            TransactionDAO dao =
                    new TransactionDAO();

            dao.remover(
                    selecionada
                            .getDescricao());

            lista.clear();

            lista.addAll(
                    dao.listar());

        }

    }

    @FXML
    public void atualizarTransacao(){

        Transaction selecionada =
                tabelaTransacoes
                        .getSelectionModel()
                        .getSelectedItem();

        if(selecionada != null){

            Transaction nova =
                    new Transaction(

                            "Editado",

                            selecionada
                                    .getValor()
                                    +100,

                            true,

                            LocalDate.now()
                    );

            TransactionDAO dao =
                    new TransactionDAO();

            dao.atualizar(
                    selecionada
                            .getDescricao(),

                    nova);

            lista.clear();

            lista.addAll(
                    dao.listar());

        }

    }
}