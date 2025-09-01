package service;

import dao.AmigoDAO;
import java.util.ArrayList;
import modelo.Amigo;
import java.util.List;
import modelo.Emprestimo;

public class AmigoService {

    private AmigoDAO amigoDAO = new AmigoDAO();

    public List<Amigo> listaAmigo() {
        return amigoDAO.getListaAmigo();
    }

    public boolean insertAmigoDB(String nome, String telefone) {
        int maiorID = amigoDAO.maiorIDAmigo() + 1;
        Amigo amigo = new Amigo(maiorID, nome, telefone);
        amigoDAO.insertAmigoDB(amigo);
        return true;
    }

    public boolean deleteAmigoDB(int id) {
        amigoDAO.deleteAmigoDB(id);
        return true;
    }

    public boolean updateAmigoDB(int id, String nome, String telefone) {
        Amigo amigo = new Amigo(id, nome, telefone);
        amigoDAO.updateAmigoDB(amigo);
        return true;
    }

    public Amigo retrieveAmigoDB(int id) {
        return amigoDAO.retrieveAmigoDB(id);
    }

    public int maiorID() {
        return amigoDAO.maiorIDAmigo();
    }

    private List<Emprestimo> buscarEmprestimosDoAmigo(int id, boolean apenasAtivos) {
        EmprestimoService emp = new EmprestimoService();
        List<Emprestimo> lista = apenasAtivos ? emp.getListaEmprestimoAtivo()
                : emp.listaEmprestimo();

        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : lista) {
            if (e.getIDAmigo() == id) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public boolean possuiEmprestimoAtivo(int id) {
        return !buscarEmprestimosDoAmigo(id, true).isEmpty();
    }

    public int quantidadeEmprestimo(int id) {
        return buscarEmprestimosDoAmigo(id, false).size();
    }

    public String getNomeAmigo(int id) {
        for (Amigo a : listaAmigo()) {
            if (a.getIdAmigo() == id) {
                return a.getNomeAmigo();
            }
        }
        return "";
    }
}
