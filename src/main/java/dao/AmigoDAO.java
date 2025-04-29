package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Amigo;

/**
 * Classe responsável pelo acesso aos dados dos amigos no banco de dados.
 */
public class AmigoDAO extends ConexaoDAO {

    /**
     * Lista de amigos em memória.
     */
    public static ArrayList<Amigo> listaAmigo = new ArrayList<>();

    /**
     * Obtém a lista de amigos do banco de dados.
     *
     * @return Lista de amigos.
     */
    public ArrayList<Amigo> getListaAmigo() {
        listaAmigo.clear();
        try {
            Statement smt = super.getConexao().createStatement();
            ResultSet res = smt.executeQuery("select * from tb_amigo");
            while (res.next()) {
                int idAmigo = res.getInt("IdAmigo");
                String nomeAmigo = res.getString("nomeAmigo");
                String telefoneAmigo = res.getString("telefoneAmigo");
                Amigo objeto = new Amigo(idAmigo, nomeAmigo, telefoneAmigo);
                listaAmigo.add(objeto);
            }
            smt.close();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
        }
        return listaAmigo;
    }

    /**
     * Define a lista de amigos.
     *
     * @param listaAmigo Lista de amigos a ser definida.
     */
    public static void setListaAmigo(ArrayList<Amigo> listaAmigo) {
        AmigoDAO.listaAmigo = listaAmigo;
    }

    /**
     * Obtém o maior ID de amigo no banco de dados.
     *
     * @return Maior ID de amigo.
     */
    public int maiorIDAmigo() {
        int MaiorID = 0;
        try {
            Statement smt = super.getConexao().createStatement();
            ResultSet res = smt.executeQuery("select MAX(idAmigo)idAmigo from tb_amigo");
            res.next();
            MaiorID = res.getInt("idAmigo");
            smt.close();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
        }
        return MaiorID;
    }

    /**
     * Insere um amigo no banco de dados.
     *
     * @param amigo Amigo a ser inserido.
     * @return {@code true} se a inserção for bem-sucedida, caso contrário,
     * lança uma exceção.
     */
    public boolean insertAmigoDB(Amigo amigo) {
        String res = "insert into tb_amigo(idAmigo,nomeAmigo,telefoneAmigo)values(?,?,?)";
        try {
            PreparedStatement smt = super.getConexao().prepareCall(res);
            smt.setInt(1, amigo.getIdAmigo());
            smt.setString(2, amigo.getNomeAmigo());
            smt.setString(3, amigo.getTelefone());
            smt.execute();
            smt.close();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Recupera um amigo do banco de dados com base no ID.
     *
     * @param IdAmigo ID do amigo a ser recuperado.
     * @return Amigo recuperado.
     */
    public Amigo retrieveAmigoDB(int IdAmigo) {
        Amigo amigo = new Amigo();
        amigo.setIdAmigo(IdAmigo);
        try {
            Statement smt = super.getConexao().createStatement();
            ResultSet res = smt.executeQuery("select * from tb_amigo where idAmigo = " + IdAmigo);
            res.next();
            amigo.setNomeAmigo(res.getString("nomeAmigo"));
            amigo.setTelefone(res.getString("telefoneAmigo"));
            smt.close();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
        }
        return amigo;
    }

    /**
     * Atualiza as informações de um amigo no banco de dados.
     *
     * @param amigo Amigo a ser atualizado.
     * @return {@code true} se a atualização for bem-sucedida, caso contrário,
     * lança uma exceção.
     */
    public boolean updateAmigoDB(Amigo amigo) {
        String res = "update tb_amigo set idAmigo=?,nomeAmigo=?,telefoneAmigo=? where idAmigo=?";
        try {
            PreparedStatement smt = super.getConexao().prepareStatement(res);
            smt.setInt(1, amigo.getIdAmigo());
            smt.setString(2, amigo.getNomeAmigo());
            smt.setString(3, amigo.getTelefone());
            smt.setInt(4, amigo.getIdAmigo());
            smt.execute();
            smt.close();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Remove um amigo do banco de dados com base no ID.
     *
     * @param IdAmigo ID do amigo a ser removido.
     * @return {@code true} se a remoção for bem-sucedida, caso contrário,
     * {@code false}.
     */
    public boolean deleteAmigoDB(int IdAmigo) {
        try {
            Statement smt = super.getConexao().createStatement();
            smt.executeUpdate("delete from tb_amigo where idAmigo = " + IdAmigo);
            smt.close();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
        }
        return true;
    }
}
