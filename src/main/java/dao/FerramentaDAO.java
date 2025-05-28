package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Ferramenta;

/**
 * Classe responsável pelo acesso aos dados das ferramentas no banco de dados.
 */
public class FerramentaDAO extends ConexaoDAO {

    /**
     * Lista de ferramentas em armazenamento.
     */
    public static ArrayList<Ferramenta> listaFerramenta = new ArrayList<>();

    public FerramentaDAO() {
        criar();
    }

    /**
     * Obtém a lista de ferramentas do banco de dados.
     *
     * @return Lista de ferramentas.
     */
    public ArrayList<Ferramenta> getListaFerramenta() {
        listaFerramenta.clear();

        String sql = "SELECT * FROM ferramenta";

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
            System.out.println("Erro: " + erro.getMessage());
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
            Statement stmt = super.getConexao().createStatement();
            ResultSet res = stmt.executeQuery(sql)
        ) {
            if (res.next()) {
                maiorID = res.getInt("idFerramenta");
            }
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
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
        String res = "insert into ferramenta(idFerramenta,nomeFerramenta,marcaFerramenta,custoFerramenta)values('" + ferramenta.getIdFerramenta() + "','" + ferramenta.getNomeFerramenta() + "','" + ferramenta.getMarcaFerramenta() + "','" + ferramenta.getCustoFerramenta() + "')";
        try {
            Statement smt = super.getConexao().createStatement();
            smt.executeUpdate(res);
            smt.close();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Recupera uma ferramenta do banco de dados com base no ID.
     *
     * @param IdFerramenta ID da ferramenta a ser recuperada.
     * @return Ferramenta recuperada.
     */
    public Ferramenta retrieveFerramentaDB(int IdFerramenta) {
        Ferramenta ferramenta = new Ferramenta();
        ferramenta.setIdFerramenta(IdFerramenta);
        try {
            Statement smt = super.getConexao().createStatement();
            ResultSet res = smt.executeQuery("select * from ferramenta where idFerramenta = " + IdFerramenta);
            res.next();
            ferramenta.setNomeFerramenta(res.getString("nomeFerramenta"));
            ferramenta.setMarcaFerramenta(res.getString("marcaFerramenta"));
            ferramenta.setCustoFerramenta(res.getDouble("custoFerramenta"));
            smt.close();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
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
            System.out.println("Erro: " + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Remove uma ferramenta do banco de dados com base no ID.
     *
     * @param IdFerramenta ID da ferramenta a ser removida.
     * @return {@code true} se a remoção for bem-sucedida, caso contrário,
     * {@code false}.
     */
    public boolean deleteFerramentaDB(int IdFerramenta) {
        try {
            Statement smt = super.getConexao().createStatement();
            smt.executeUpdate("delete from ferramenta where idFerramenta=" + IdFerramenta);
            smt.close();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
        }
        return true;
    }

    private void criar() {
        try {
            try (Connection con = getConexao(); Statement stmt = con.createStatement()) {
                //Cria a tabela senão existir
                stmt.execute("create table IF NOT EXISTS ferramenta (idFerramenta integer PRIMARY KEY, nomeFerramenta varchar(45), marcaFerramenta varchar(45), custoFerramenta real);");
            }
        } catch (SQLException e) {
            System.out.println("Erro no criar:{0}" + e.toString());
        }
    }
}
