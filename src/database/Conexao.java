package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static final String URL =
            "jdbc:sqlite:fintrack.db";

    public static Connection conectar() {

        try {

            Connection conexao =
                    DriverManager
                            .getConnection(URL);

            System.out.println(
                    "Banco conectado");

            return conexao;

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

}