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

    public boolean possuiEmprestimoAtivo(int id) {
        boolean emprestimoAtivo = false;

        EmprestimoService emp = new EmprestimoService();

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
        EmprestimoService emp = new EmprestimoService();
        List<Emprestimo> listaEmprestimo = emp.listaEmprestimo();
        for (int i = 0; i < listaEmprestimo.size(); i++) {
            if (listaEmprestimo.get(i).getIDAmigo() == id) {
                som++;
            }
        }
        return som;
    }

    public String getNomeAmigo(int id) {
        String nome = "";
        List<Amigo> listaAmigo = this.listaAmigo();
        for (int i = 0; i < listaAmigo.size(); i++) {
            if (id == listaAmigo.get(i).getIdAmigo()) {
                nome = listaAmigo.get(i).getNomeAmigo();
            }
        }
        return nome;
    }
}
