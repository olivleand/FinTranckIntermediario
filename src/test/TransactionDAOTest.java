package test;

import DatAcessObject.TransactionDAO;
import model.Transaction;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionDAOTest {

    @Test
    public void testeSalvar() {
        TransactionDAO dao = new TransactionDAO();

        Transaction t = new Transaction(
                "JUnit",
                500,
                true,
                LocalDate.now()
        );

        dao.salvar(t);

        assertNotNull(t);
    }

}