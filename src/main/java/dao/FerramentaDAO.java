package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Ferramenta;

/**
 * Classe responsável pelo acesso aos dados das ferramentas no banco de dados.
 */
public class FerramentaDAO extends ConexaoDAO {

    /**
     * Lista de ferramentas em armazenamento.
     */
    public static ArrayList<Ferramenta> listaFerramenta = new ArrayList<>();
    private static final String MENSAGEM_ERRO = "Erro: ";
    private static final Logger logger = Logger.getLogger(AmigoDAO.class.getName());

    public FerramentaDAO() {
        criar();
    }

    private void logErro(Exception e) {
        logger.log(Level.SEVERE, MENSAGEM_ERRO, e);
    }

    /**
     * Obtém a lista de ferramentas do banco de dados.
     *
     * @return Lista de ferramentas.
     */
    public ArrayList<Ferramenta> getListaFerramenta() {
        listaFerramenta.clear();

        String sql = "SELECT idFerramenta, nomeFerramenta, marcaFerramenta, custoFerramenta FROM ferramenta";

        try (
                Connection conn = super.getConexao(); Statement smt = conn.createStatement(); ResultSet res = smt.executeQuery(sql)) {
            while (res.next()) {
                int idFerramenta = res.getInt("IdFerramenta");
                String nomeFerramenta = res.getString("nomeFerramenta");
                String marcaFerramenta = res.getString("marcaFerramenta");
                double custoFerramenta = res.getDouble("custoFerramenta");

                Ferramenta objeto = new Ferramenta(idFerramenta, nomeFerramenta, custoFerramenta, marcaFerramenta);
                listaFerramenta.add(objeto);
            }
        } catch (SQLException erro) {
            logErro(erro);
        }

        return listaFerramenta;
    }

    /**
     * Define a lista de ferramentas.
     *
     * @param listaFerramenta Lista de ferramentas a ser definida.
     */
    public static void setListaFerramenta(ArrayList<Ferramenta> listaFerramenta) {
        FerramentaDAO.listaFerramenta = listaFerramenta;
    }

    /**
     * Obtém o maior ID de ferramenta no banco de dados.
     *
     * @return Maior ID de ferramenta.
     */
    public int maiorIDFerramenta() {
        int maiorID = 0;

        String sql = "SELECT MAX(idFerramenta) AS idFerramenta FROM ferramenta";

        try (
                Statement stmt = super.getConexao().createStatement(); ResultSet res = stmt.executeQuery(sql)) {
            if (res.next()) {
                maiorID = res.getInt("idFerramenta");
            }
        } catch (SQLException erro) {
            logErro(erro);
        }

        return maiorID;
    }

    /**
     * Insere uma ferramenta no banco de dados.
     *
     * @param ferramenta Ferramenta a ser inserida.
     * @return {@code true} se a inserção for bem-sucedida, caso contrário,
     * lança uma exceção.
     */
    public boolean insertFerramentaDB(Ferramenta ferramenta) {
        String res = "insert into ferramenta(idFerramenta,nomeFerramenta,marcaFerramenta,custoFerramenta)values(?,?,?,?)";
        try {
            PreparedStatement smt = super.getConexao().prepareStatement(res);;
            smt.setInt(1, ferramenta.getIdFerramenta());
            smt.setString(2, ferramenta.getNomeFerramenta());
            smt.setString(3, ferramenta.getMarcaFerramenta());
            smt.setDouble(4, ferramenta.getCustoFerramenta());
            smt.executeUpdate();
            smt.close();
            return true;
        } catch (SQLException erro) {
            logErro(erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Recupera uma ferramenta do banco de dados com base no ID.
     *
     * @param idFerramenta ID da ferramenta a ser recuperada.
     * @return Ferramenta recuperada.
     */
    public Ferramenta retrieveFerramentaDB(int idFerramenta) {
        Ferramenta ferramenta = new Ferramenta();
        ferramenta.setIdFerramenta(idFerramenta);

        String sql = "SELECT idFerramenta, nomeFerramenta, marcaFerramenta, custoFerramenta FROM ferramenta WHERE idFerramenta = ?";

        try (
                PreparedStatement pstmt = super.getConexao().prepareStatement(sql)) {
            pstmt.setInt(1, idFerramenta);
            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    ferramenta.setNomeFerramenta(res.getString("nomeFerramenta"));
                    ferramenta.setMarcaFerramenta(res.getString("marcaFerramenta"));
                    ferramenta.setCustoFerramenta(res.getDouble("custoFerramenta"));
                }
            }
        } catch (SQLException erro) {
            logErro(erro);
        }

        return ferramenta;
    }

    /**
     * Atualiza as informações de uma ferramenta no banco de dados.
     *
     * @param ferramenta Ferramenta a ser atualizada.
     * @return {@code true} se a atualização for bem-sucedida, caso contrário,
     * lança uma exceção.
     */
    public boolean updateFerramentaDB(Ferramenta ferramenta) {
        String res = "update ferramenta set idFerramenta=?,nomeFerramenta=?, marcaFerramenta=?, custoFerramenta=? where idFerramenta=?";
        try {
            PreparedStatement smt = super.getConexao().prepareStatement(res);
            smt.setInt(1, ferramenta.getIdFerramenta());
            smt.setString(2, ferramenta.getNomeFerramenta());
            smt.setString(3, ferramenta.getMarcaFerramenta());
            smt.setDouble(4, ferramenta.getCustoFerramenta());
            smt.setInt(5, ferramenta.getIdFerramenta());
            smt.execute();
            smt.close();
            return true;
        } catch (SQLException erro) {
            logErro(erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Remove uma ferramenta do banco de dados com base no ID.
     *
     * @param idFerramenta ID da ferramenta a ser removida.
     * @return {@code true} se a remoção for bem-sucedida, caso contrário,
     * {@code false}.
     */
    public boolean deleteFerramentaDB(int idFerramenta) {
        String sql = "DELETE FROM ferramenta WHERE idFerramenta = ?";

        try (PreparedStatement pstmt = super.getConexao().prepareStatement(sql)) {
            pstmt.setInt(1, idFerramenta);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException erro) {
            logErro(erro);
            return false;
        }
    }

    private void criar() {
        try {
            try (Connection con = getConexao(); Statement stmt = con.createStatement()) {
                //Cria a tabela senão existir
                stmt.execute("create table IF NOT EXISTS ferramenta (idFerramenta integer PRIMARY KEY, nomeFerramenta varchar(45), marcaFerramenta varchar(45), custoFerramenta real);");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro no criar: {0}", e.toString());
        }
    }
}
