package DatAcessObject;

import database.Conexao;
import model.Transaction;
import repository.RepositorioGeneric;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;

import repository.RepositorioGeneric;

public class TransactionDAO
        implements
        RepositorioGeneric<Transaction> {

    public void criarTabela() {

        String sql = """
                CREATE TABLE IF NOT EXISTS transacoes(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    descricao TEXT,
                    valor REAL,
                    receita BOOLEAN,
                    data TEXT
                )
                """;

        try {

            Connection conexao =
                    Conexao.conectar();

            Statement statement =
                    conexao.createStatement();

            statement.execute(sql);

            System.out.println(
                    "Tabela criada");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public void salvar(
            Transaction transaction) {

        String sql =
                """
                
                        INSERT INTO transacoes(
                descricao,
                valor,
                receita,
    
                                   data)

                VALUES(?,?,?,?)
                """;

        try {

            Connection conexao =
                    Conexao.conectar();

            PreparedStatement ps =
                    conexao.prepareStatement(
                            sql);

            ps.setString(
                    1,
                    transaction
                            .getDescricao());

            ps.setDouble(
                    2,
                    transaction
                            .getValor());

            ps.setBoolean(
                    3,
                    transaction
                            .isEhReceita());

            ps.setString(
                    4,
                    transaction
                            .getData()
                            .toString());

            ps.executeUpdate();

            System.out.println(
                    "Salvo");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public List<Transaction> listar() {

        List<Transaction> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM transacoes";

        try {

            Connection conexao =
                    Conexao.conectar();

            Statement st =
                    conexao.createStatement();

            ResultSet rs =
                    st.executeQuery(sql);

            while(rs.next()){

                Transaction t =
                        new Transaction(

                                rs.getString(
                                        "descricao"),

                                rs.getDouble(
                                        "valor"),

                                rs.getBoolean(
                                        "receita"),

                                LocalDate.parse(
                                        rs.getString(
                                                "data"))
                        );

                lista.add(t);

            }

        } catch(Exception e){

            e.printStackTrace();

        }

        return lista;

    }

    public void remover(String descricao){

        String sql =
                "DELETE FROM transacoes WHERE descricao=?";

        try{

            Connection conexao =
                    Conexao.conectar();

            PreparedStatement ps =
                    conexao.prepareStatement(
                            sql);

            ps.setString(
                    1,
                    descricao);

            ps.executeUpdate();

            System.out.println(
                    "Removido");

        }catch(Exception e){

            e.printStackTrace();

        }

    }

    public void atualizar(
            String descricaoAntiga,
            Transaction nova){

        String sql =
                """
                UPDATE transacoes
    
                SET descricao=?,
                    valor=?,
                    receita=?,
                    data=?
    
                WHERE descricao=?
                """;

        try{

            Connection conexao =
                    Conexao.conectar();

            PreparedStatement ps =
                    conexao.prepareStatement(
                            sql);

            ps.setString(
                    1,
                    nova.getDescricao());

            ps.setDouble(
                    2,
                    nova.getValor());

            ps.setBoolean(
                    3,
                    nova.isEhReceita());

            ps.setString(
                    4,
                    nova.getData()
                            .toString());

            ps.setString(
                    5,
                    descricaoAntiga);

            ps.executeUpdate();

            System.out.println(
                    "Atualizado");

        }catch(Exception e){

            e.printStackTrace();

        }

    }

}


