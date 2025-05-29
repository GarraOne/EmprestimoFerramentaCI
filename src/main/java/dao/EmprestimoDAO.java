package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Emprestimo;

public class EmprestimoDAO extends ConexaoDAO {

    // Lista para armazenar os dados dos empréstimos
    public static ArrayList<Emprestimo> listaEmprestimo = new ArrayList<>();

    private static final String MENSAGEM_ERRO = "Erro: ";
    private static final Logger logger = Logger.getLogger(EmprestimoDAO.class.getName());

    public EmprestimoDAO() {
        criar();
    }

    private void logErro(Exception e) {
        logger.log(Level.SEVERE, MENSAGEM_ERRO, e);
    }
    private static final String ID_EMPRESTIMO = "idEmprestimo";
    private static final String ID_AMIGO = "idAmigo";
    private static final String ID_FERRAMENTA = "idFerramenta";
    private static final String DATA_INICIO = "dataInicio";
    private static final String DATA_DEVOLUCAO = "dataDevolucao";

    public ArrayList<Emprestimo> getListaEmprestimo() {
        // Limpa a lista para evitar duplicatas
        listaEmprestimo.clear();
        try (Statement smt = super.getConexao().createStatement()) {
            // Cria uma declaração para executar a consulta SQL
            ResultSet res = smt.executeQuery("select idEmprestimo, idAmigo, idFerramenta, dataInicio, dataDevolucao from emprestimo");

            // Itera sobre o resultado da consulta e adiciona empréstimos à lista
            while (res.next()) {
                int idEmprestimo = res.getInt(ID_EMPRESTIMO);
                int idAmigo = res.getInt(ID_AMIGO);
                int idFerramenta = res.getInt(ID_FERRAMENTA);
                String dataEmprestimo = res.getString(DATA_INICIO);
                String dataDevolucao = res.getString(DATA_DEVOLUCAO);
                Emprestimo objeto = new Emprestimo(idEmprestimo, idAmigo, idFerramenta, dataEmprestimo, dataDevolucao);
                listaEmprestimo.add(objeto);
            }
            // Fecha a declaração após a execução da consulta
        } catch (SQLException erro) {
            logErro(erro);
        }
        // Retorna a lista de empréstimos
        return listaEmprestimo;

    }

    public static void setListaEmprestimo(ArrayList<Emprestimo> listaEmprestimo) {
        EmprestimoDAO.listaEmprestimo = listaEmprestimo;

    }

    public int maiorIDEmprestimo() {
        int maiorID = 0;
        try (Statement smt = super.getConexao().createStatement()) {
            ResultSet res = smt.executeQuery("select MAX(idEmprestimo)idEmprestimo from emprestimo");
            res.next();
            maiorID = res.getInt(ID_EMPRESTIMO);
        } catch (SQLException erro) {
            logErro(erro);
        }
        return maiorID;
    }

    public boolean insertEmprestimoDB(Emprestimo emprestimo) {
        String res = "insert into emprestimo(idEmprestimo,idFerramenta,idAmigo,dataInicio,dataDevolucao)values(?,?,?,?,?)";
        try {
            PreparedStatement smt = super.getConexao().prepareStatement(res);
            smt.setInt(1, emprestimo.getIDEmprestimo());
            smt.setInt(2, emprestimo.getIDFerramenta());
            smt.setInt(3, emprestimo.getIDAmigo());
            smt.setString(4, emprestimo.getDataEmprestimo());
            smt.setString(5, emprestimo.getDataDevolucao());
            smt.executeUpdate();
            smt.close();
            return true;
        } catch (SQLException erro) {
            logErro(erro);
            throw new DAOException("Erro ao inserir empréstimo", erro);
        }
    }

    public Emprestimo retrieveEmprestimoDB(int idEmprestimo) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIDEmprestimo(idEmprestimo);
        String query = "select idEmprestimo, idAmigo, idFerramenta, dataInicio, dataDevolucao from emprestimo where idEmprestimo = ?";
        try (PreparedStatement smt = super.getConexao().prepareStatement(query)) {
            smt.setInt(1, emprestimo.getIDEmprestimo());
            ResultSet res = smt.executeQuery();
            res.next();
            emprestimo.setIDEmprestimo(res.getInt(ID_EMPRESTIMO));
            emprestimo.setDataDevolucao(res.getString(DATA_DEVOLUCAO));
            emprestimo.setDataEmprestimo(res.getString(DATA_INICIO));
            emprestimo.setIDAmigo(res.getInt(ID_AMIGO));
            emprestimo.setIDFerramenta(res.getInt(ID_FERRAMENTA));
        } catch (SQLException erro) {
            logErro(erro);
        }
        return emprestimo;
    }

    public boolean updateEmprestimoDB(Emprestimo emprestimo) {
        String res = "update emprestimo set idEmprestimo=?,idFerramenta=?,idAmigo=?,dataInicio=?,dataDevolucao=? where idEmprestimo=?";
        try {
            PreparedStatement smt = super.getConexao().prepareStatement(res);
            smt.setInt(1, emprestimo.getIDEmprestimo());
            smt.setInt(2, emprestimo.getIDFerramenta());
            smt.setInt(3, emprestimo.getIDAmigo());
            smt.setString(4, emprestimo.getDataEmprestimo());
            smt.setString(5, emprestimo.getDataDevolucao());
            smt.setInt(6, emprestimo.getIDEmprestimo());
            smt.execute();
            smt.close();
            return true;
        } catch (SQLException erro) {
            logErro(erro);
            throw new DAOException("Erro ao atualizar empréstimo", erro);
        }
    }

    public boolean deleteEmprestimoDB(int idEmprestimo) {
        String res = "delete from emprestimo where idEmprestimo=?";
        try (PreparedStatement smt = super.getConexao().prepareStatement(res)) {
            smt.setInt(1, idEmprestimo);
            smt.executeUpdate();
        } catch (SQLException erro) {
            logErro(erro);
        }
        return true;
    }

    private void criar() {
        try {
            try (Connection con = getConexao(); Statement stmt = con.createStatement()) {
                //Cria a tabela senão existir
                stmt.execute("create table IF NOT EXISTS emprestimo (idEmprestimo integer PRIMARY KEY, idFerramenta integer, idAmigo integer,dataInicio text, dataDevolucao text);");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro no criar: {0}", e.toString());
        }
    }
}
