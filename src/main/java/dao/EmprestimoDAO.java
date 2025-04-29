package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Emprestimo;

public class EmprestimoDAO extends ConexaoDAO {

    // Lista para armazenar os dados dos empréstimos
    public static ArrayList<Emprestimo> listaEmprestimo = new ArrayList<>();

    public ArrayList<Emprestimo> getListaEmprestimo() {
        // Limpa a lista para evitar duplicatas

        listaEmprestimo.clear();
        try {
            // Cria uma declaração para executar a consulta SQL
            Statement smt = super.getConexao().createStatement();
            ResultSet res = smt.executeQuery("select * from tb_emprestimo");

            // Itera sobre o resultado da consulta e adiciona empréstimos à lista
            while (res.next()) {
                int idEmprestimo = res.getInt("IdEmprestimo");
                int idAmigo = res.getInt("idAmigo");
                int idFerramenta = res.getInt("idFerramenta");
                String dataEmprestimo = res.getString("dataInicio");
                String dataDevolucao = res.getString("dataDevolucao");
                Emprestimo objeto = new Emprestimo(idEmprestimo, idAmigo, idFerramenta, dataEmprestimo, dataDevolucao);

                listaEmprestimo.add(objeto);
            }
            // Fecha a declaração após a execução da consulta
            smt.close();
        } catch (SQLException erro) {
            // Trata o erro caso ocorra algum problema na execução da consulta
            System.out.println("Erro: " + erro);
        }
        // Retorna a lista de empréstimos
        return listaEmprestimo;

    }

    public static void setListaEmprestimo(ArrayList<Emprestimo> listaEmprestimo) {
        EmprestimoDAO.listaEmprestimo = listaEmprestimo;

    }

    public int maiorIDEmprestimo() {
        int MaiorID = 0;
        try {
            Statement smt = super.getConexao().createStatement();
            ResultSet res = smt.executeQuery("select MAX(idEmprestimo)idEmprestimo from tb_emprestimo");
            res.next();
            MaiorID = res.getInt("idEmprestimo");
            smt.close();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
        }
        return MaiorID;
    }

    public boolean insertEmprestimoDB(Emprestimo emprestimo) {
        String res = "insert into tb_emprestimo(idEmprestimo,idAmigo,idFerramenta,dataInicio,dataDevolucao)values(?,?,?,?,?)";
        try {
            PreparedStatement smt = super.getConexao().prepareCall(res);
            smt.setInt(1, emprestimo.getIDEmprestimo());
            smt.setInt(2, emprestimo.getIDAmigo());
            smt.setInt(3, emprestimo.getIDFerramenta());
            smt.setString(4, emprestimo.getDataEmprestimo());
            smt.setString(5, emprestimo.getDataDevolucao());
            smt.execute();
            smt.close();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            throw new RuntimeException(erro);
        }
    }

    public Emprestimo retrieveEmprestimoDB(int IdEmprestimo) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIDEmprestimo(IdEmprestimo);
        try {
            Statement smt = super.getConexao().createStatement();
            ResultSet res = smt.executeQuery("select * from tb_emprestimo where idEmprestimo = " + IdEmprestimo);
            res.next();
            emprestimo.setIDEmprestimo(res.getInt("idEmprestimo"));
            emprestimo.setDataDevolucao(res.getString("dataDevolucao"));
            emprestimo.setDataEmprestimo(res.getString("dataInicio"));
            emprestimo.setIDAmigo(res.getInt("idAmigo"));
            emprestimo.setIDFerramenta(res.getInt("idFerramenta"));
            smt.close();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
        }
        return emprestimo;
    }

    public boolean updateEmprestimoDB(Emprestimo emprestimo) {
        String res = "update tb_emprestimo set idEmprestimo=?,idAmigo=?, idFerramenta=?, dataInicio=?, dataDevolucao=? where idEmprestimo=?";
        try {
            PreparedStatement smt = super.getConexao().prepareStatement(res);
            smt.setInt(1, emprestimo.getIDEmprestimo());
            smt.setInt(2, emprestimo.getIDAmigo());
            smt.setInt(3, emprestimo.getIDFerramenta());
            smt.setString(4, emprestimo.getDataEmprestimo());
            smt.setString(5, emprestimo.getDataDevolucao());
            smt.setInt(6, emprestimo.getIDEmprestimo());
            smt.execute();
            smt.close();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            throw new RuntimeException(erro);
        }
    }

    public boolean deleteEmprestimoDB(int IdEmprestimo) {
        try {
            Statement smt = super.getConexao().createStatement();
            smt.executeUpdate("delete from tb_emprestimo where idEmprestimo=" + IdEmprestimo);
            smt.close();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
        }
        return true;
    }

    // Outros métodos de manipulação de empréstimos
}
