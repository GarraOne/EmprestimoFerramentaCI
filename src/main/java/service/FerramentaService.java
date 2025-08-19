package service;

import dao.FerramentaDAO;
import java.util.ArrayList;
import modelo.Ferramenta;
import java.util.List;
import modelo.Emprestimo;

public class FerramentaService {

    private FerramentaDAO ferramentaDAO = new FerramentaDAO();

    public List<Ferramenta> listaFerramenta() {
        return ferramentaDAO.getListaFerramenta();
    }

    public boolean insertFerramentaDB(String nome, String marca, double custo) {
        int maiorID = ferramentaDAO.maiorIDFerramenta() + 1;
        Ferramenta ferramenta = new Ferramenta(maiorID, nome, custo, marca);
        ferramentaDAO.insertFerramentaDB(ferramenta);
        return true;
    }

    public boolean deleteFerramentaDB(int id) {
        ferramentaDAO.deleteFerramentaDB(id);
        return true;
    }

    public boolean updateFerramentaDB(int id, String nome, String marca, double custo) {
        Ferramenta ferramenta = new Ferramenta(id, nome, custo, marca);
        ferramentaDAO.updateFerramentaDB(ferramenta);
        return true;
    }

    public Ferramenta retrieveFerramentaDB(int id) {
        return ferramentaDAO.retrieveFerramentaDB(id);
    }

    public int maiorID() {
        return ferramentaDAO.maiorIDFerramenta();
    }

    public boolean possuiEmprestimoAtivo(int id) {
        boolean emprestimoAtivo = false;

        Emprestimo emp = new Emprestimo();

        ArrayList<Emprestimo> listaEmprestimo = emp.getListaEmprestimoAtivo();
        for (int i = 0; i < listaEmprestimo.size(); i++) {
            if (listaEmprestimo.get(i).getIDAmigo() == id) {
                emprestimoAtivo = true;
            }
        }
        return emprestimoAtivo;
    }

    public int quantidadeEmprestimo(int id) {
        int som = 0;
        Emprestimo emp = new Emprestimo();
        List<Emprestimo> listaEmprestimo = emp.listaEmprestimo();
        for (int i = 0; i < listaEmprestimo.size(); i++) {
            if (listaEmprestimo.get(i).getIDAmigo() == id) {
                som++;
            }
        }
        return som;
    }

    public String getDisponivel(int id) {
        String disponivel = "Sim";
        Emprestimo emp = new Emprestimo();
        ArrayList<Emprestimo> listaEmprestimoAtivo = emp.getListaEmprestimoAtivo();
        for (int i = 0; i < listaEmprestimoAtivo.size(); i++) {
            if (listaEmprestimoAtivo.get(i).getIDFerramenta() == id) {
                disponivel = "Não";
            }
        }
        return disponivel;
    }

    public String getNomeFerramenta(int id) {
        String nome = "";
        List<Ferramenta> listaFerramenta = this.listaFerramenta();
        for (int i = 0; i < listaFerramenta.size(); i++) {
            if (id == listaFerramenta.get(i).getIdFerramenta()) {
                nome = listaFerramenta.get(i).getNomeFerramenta();
            }
        }
        return nome;
    }
}
