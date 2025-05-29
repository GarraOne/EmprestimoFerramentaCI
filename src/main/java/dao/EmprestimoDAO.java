package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Emprestimo;

public class EmprestimoDAO extends ConexaoDAO {

    // Lista para armazenar os dados dos empréstimos
    public static ArrayList<Emprestimo> listaEmprestimo = new ArrayList<>();

    private static final String MENSAGEM_ERRO = "Erro: ";

    public EmprestimoDAO() {
        criar();
    }

    private void logErro(Exception e) {
        System.out.println(MENSAGEM_ERRO + e);
    }

    private static final String idAmigoCostante = "idAmigo";
    private static final String idFerramentaCostante = "idFerramenta";
    private static final String dataInicioCostante = "dataInicio";
    private static final String dataDevolucaoCostante = "dataDevolucao";
    private static final String emprestimoIdCostante = "idEmprestimo";

    public ArrayList<Emprestimo> getListaEmprestimo() {
        // Limpa a lista para evitar duplicatas
        listaEmprestimo.clear();
        try (Statement smt = super.getConexao().createStatement()) {
            // Cria uma declaração para executar a consulta SQL
            ResultSet res = smt.executeQuery("select idEmprestimo, idAmigo, idFerramenta, dataInicio, dataDevolucao from emprestimo");

            // Itera sobre o resultado da consulta e adiciona empréstimos à lista
            while (res.next()) {
                int idEmprestimo = res.getInt(emprestimoIdCostante);
                int idAmigo = res.getInt(idAmigoCostante);
                int idFerramenta = res.getInt(idFerramentaCostante);
                String dataEmprestimo = res.getString(dataInicioCostante);
                String dataDevolucao = res.getString(dataDevolucaoCostante);
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
            maiorID = res.getInt(emprestimoIdCostante);
        } catch (SQLException erro) {
            logErro(erro);
        }
        return maiorID;
    }

    public boolean insertEmprestimoDB(Emprestimo emprestimo) {
        String res = "insert into emprestimo(idEmprestimo,idFerramenta,idAmigo,dataInicio,dataDevolucao)values('" + emprestimo.getIDEmprestimo() + "','" + emprestimo.getIDFerramenta() + "','" + emprestimo.getIDAmigo() + "','" + emprestimo.getDataEmprestimo() + "','" + emprestimo.getDataDevolucao() + "')";
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

    public Emprestimo retrieveEmprestimoDB(int idEmprestimo) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIDEmprestimo(idEmprestimo);
        try (Statement smt = super.getConexao().createStatement()) {
            ResultSet res = smt.executeQuery("select idEmprestimo, idAmigo, idFerramenta, dataInicio, dataDevolucao from emprestimo where idEmprestimo = " + idEmprestimo);
            res.next();
            emprestimo.setIDEmprestimo(res.getInt(emprestimoIdCostante));
            emprestimo.setDataDevolucao(res.getString(dataDevolucaoCostante));
            emprestimo.setDataEmprestimo(res.getString(dataInicioCostante));
            emprestimo.setIDAmigo(res.getInt(idAmigoCostante));
            emprestimo.setIDFerramenta(res.getInt(idFerramentaCostante));
        } catch (SQLException erro) {
            logErro(erro);
        }
        return emprestimo;
    }

    public boolean updateEmprestimoDB(Emprestimo emprestimo) {
        String res = "update emprestimo set idEmprestimo=?,idFerramenta=?,idAmigo=?,dataInicio=?,dataDevolucao=? where idEmprestimo=?";
        try {
            System.out.println(emprestimo.getDataDevolucao());
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
            throw new RuntimeException(erro);
        }
    }

    public boolean deleteEmprestimoDB(int idEmprestimo) {
        try (Statement smt = super.getConexao().createStatement()) {
            smt.executeUpdate("delete from emprestimo where idEmprestimo=" + idEmprestimo);
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
            System.out.println("Erro no criar:{0}" + e.toString());
        }
    }
}
