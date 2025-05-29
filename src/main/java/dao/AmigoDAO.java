package dao;

import java.sql.Connection;
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
    private static ArrayList<Amigo> listaAmigo = new ArrayList<>();

    private static final String MENSAGEM_ERRO = "Erro: ";

    /**
     * Obtém a lista de amigos do banco de dados.
     *
     * @return Lista de amigos.
     */
    public AmigoDAO() {
        criar();
    }

    private void logErro(Exception e) {
        System.out.println(MENSAGEM_ERRO + e);
    }

    public ArrayList<Amigo> getListaAmigo() {
        synchronized (listaAmigo) {
            listaAmigo.clear();
        }
        try (Statement smt = super.getConexao().createStatement()) {
            ResultSet res = smt.executeQuery("select idAmigo, nomeAmigo, TelefoneAmigo from amigo");
            while (res.next()) {
                int idAmigo = res.getInt("IdAmigo");
                String nomeAmigo = res.getString("nomeAmigo");
                String telefoneAmigo = res.getString("telefoneAmigo");
                Amigo objeto = new Amigo(idAmigo, nomeAmigo, telefoneAmigo);
                listaAmigo.add(objeto);
            }
        } catch (SQLException erro) {
                logErro(erro);
        }
        return new ArrayList<>(listaAmigo);
    }

    /**
     * Define a lista de amigos.
     *
     * @param listaAmigo Lista de amigos a ser definida.
     */
    public static void setListaAmigo(ArrayList<Amigo> novaLista) {
        synchronized (listaAmigo) {
            listaAmigo.clear();
            listaAmigo.addAll(novaLista);
        }
    }

    /**
     * Obtém o maior ID de amigo no banco de dados.
     *
     * @return Maior ID de amigo.
     */
    public int maiorIDAmigo() {
        int maiorID = 0;
        try (Statement smt = super.getConexao().createStatement()) {
            ResultSet res = smt.executeQuery("select MAX(idAmigo)idAmigo from amigo");
            res.next();
            maiorID = res.getInt("idAmigo");
        } catch (SQLException erro) {
                logErro(erro);
        }
        return maiorID;
    }

    /**
     * Insere um amigo no banco de dados.
     *
     * @param amigo Amigo a ser inserido.
     * @return {@code true} se a inserção for bem-sucedida, caso contrário,
     * lança uma exceção.
     */
    public boolean insertAmigoDB(Amigo amigo) {
        String res = "insert into amigo(idAmigo, nomeAmigo, telefoneAmigo) values ('" + amigo.getIdAmigo() + "','" + amigo.getNomeAmigo() + "','" + amigo.getTelefone() + "')";
        try {
            Statement smt = super.getConexao().createStatement();
            smt.executeUpdate(res);
            smt.close();
            return true;
        } catch (SQLException erro) {
                logErro(erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Recupera um amigo do banco de dados com base no ID.
     *
     * @param idAmigo ID do amigo a ser recuperado.
     * @return Amigo recuperado.
     */
    public Amigo retrieveAmigoDB(int idAmigo) {
        Amigo amigo = new Amigo();
        amigo.setIdAmigo(idAmigo);
        try (Statement smt = super.getConexao().createStatement()) {
            ResultSet res = smt.executeQuery("select idAmigo, nomeAmigo, TelefoneAmigo from amigo where idAmigo = " + idAmigo);
            res.next();
            amigo.setNomeAmigo(res.getString("nomeAmigo"));
            amigo.setTelefone(res.getString("telefoneAmigo"));
        } catch (SQLException erro) {
                logErro(erro);
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
        String res = "update amigo set idAmigo=?,nomeAmigo=?,telefoneAmigo=? where idAmigo=?";
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
                logErro(erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Remove um amigo do banco de dados com base no ID.
     *
     * @param idAmigo ID do amigo a ser removido.
     * @return {@code true} se a remoção for bem-sucedida, caso contrário,
     * {@code false}.
     */
    public boolean deleteAmigoDB(int idAmigo) {
        try (Statement smt = super.getConexao().createStatement()) {
            smt.executeUpdate("delete from amigo where idAmigo = " + idAmigo);
        } catch (SQLException erro) {
                logErro(erro);
        }
        return true;
    }

    private void criar() {
        try {
            try (Connection con = getConexao(); Statement stmt = con.createStatement()) {
                //Cria a tabela senão existir
                stmt.execute("create table IF NOT EXISTS amigo (idAmigo integer PRIMARY KEY, nomeAmigo varchar(45), telefoneAmigo varchar(12));");
            }
        } catch (SQLException e) {
            System.out.println("Erro no criar:{0}" + e.toString());
        }
    }
}
