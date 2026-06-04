package controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import DatAcessObject.TransactionDAO;
import model.Transaction;

import java.time.LocalDate;
import javafx.stage.Stage;

public class CadastroController {

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtValor;

    @FXML
    private CheckBox checkReceita;

    @FXML
    public void salvar(){

        String descricao =
                txtDescricao.getText();

        double valor =
                Double.parseDouble(
                        txtValor.getText());

        boolean receita =
                checkReceita.isSelected();

        Transaction transaction =
                new Transaction(
                        descricao,
                        valor,
                        receita,
                        LocalDate.now()
                );

        TransactionDAO dao =
                new TransactionDAO();

        dao.salvar(
                transaction);

        Stage stage =
                (Stage) txtDescricao
                        .getScene()
                        .getWindow();

        stage.close();

    }

}