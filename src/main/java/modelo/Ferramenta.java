package modelo;

import dao.FerramentaDAO;
import java.util.ArrayList;

/**
 * Classe de ferramenta com nome, marca, custo e disponibilidade.
 */
public class Ferramenta {

    private int idFerramenta;
    private String nomeFerramenta;
    private String marcaFerramenta;
    private double custoFerramenta;
    private FerramentaDAO dao;

    /**
     * Construtor para a classe Ferramenta. Inicializa a ferramenta com nome e
     * custo, define a marca como uma string vazia e define a disponibilidade
     * como verdadeira.
     *
     * @param nome O nome da ferramenta.
     * @param custo O custo da ferramenta.
     * @param idFerramenta O id da ferramenta.
     */
    public Ferramenta() {
        this(0, "", 0, "");
    }

    /**
     * public Ferramenta(int idFerramenta, String nome, double custo, String
     * marca) { this.idFerramenta = idFerramenta; this.nome = nome; this.custo =
     * custo; this.marca = marca; // Inicializa a marca como uma string vazia
     * this.disponivel = true; this.dao = new FerramentaDAO(); }
     */
    public Ferramenta(int idFerramenta, String nome, double custo, String marca) {
        this.idFerramenta = idFerramenta;
        this.nomeFerramenta = nome;
        this.custoFerramenta = custo;
        this.marcaFerramenta = marca; // Inicializa a marca como uma string vazia
        this.dao = new FerramentaDAO();
    }

    /**
     * Obtém o nome da ferramenta.
     *
     * @return O nome da ferramenta.
     */
    public String getNomeFerramenta() {
        return nomeFerramenta;
    }

    public void setNomeFerramenta(String nome) {
        this.nomeFerramenta = nome;
    }

    /**
     * Obtém o custo da ferramenta.
     *
     * @return O custo da ferramenta.
     */
    public double getCustoFerramenta() {
        return custoFerramenta;
    }

    public void setCustoFerramenta(double custo) {
        this.custoFerramenta = custo;
    }

    /**
     * Verifica se a ferramenta está disponível.
     *
     * @return {@code true} se a ferramenta estiver disponível, {@code false}
     * caso contrário.
     */
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

    /**
     * Obtém a marca da ferramenta.
     *
     * @return A marca da ferramenta.
     */
    public String getMarcaFerramenta() {
        return marcaFerramenta;
    }

    public void setMarcaFerramenta(String marca) {
        this.marcaFerramenta = marca;
    }

    public int getIdFerramenta() {
        return idFerramenta;
    }

    public void setIdFerramenta(int idFerramenta) {
        this.idFerramenta = idFerramenta;
    }

    public ArrayList<Ferramenta> listaFerramenta() {
        return dao.getListaFerramenta();
    }

    public boolean insertFerramentaDB(String nome, String marca, double custo) {
        int maiorID = dao.maiorIDFerramenta() + 1;

        Ferramenta ferramenta = new Ferramenta(maiorID, nome, custo, marca);
        dao.insertFerramentaDB(ferramenta);
        return true;

    }

    public boolean deleteFerramentaDB(int id) {
        dao.deleteFerramentaDB(id);
        return true;
    }

    public boolean updateFerramentaDB(int id, String nome, String marca, double custo) {
        Ferramenta ferramenta = new Ferramenta(id, nome, custo, marca);
        dao.updateFerramentaDB(ferramenta);
        return true;
    }

    public Ferramenta retrieveFerramentaDB(int id) {
        return dao.retrieveFerramentaDB(id);
    }

    public int maiorID() {
        return dao.maiorIDFerramenta();
    }

    public String getNomeFerramenta(int id) {
        String nome = "";
        ArrayList<Ferramenta> listaFerramenta = this.listaFerramenta();
        for (int i = 0; i < listaFerramenta.size(); i++) {
            if (id == listaFerramenta.get(i).getIdFerramenta()) {
                nome = listaFerramenta.get(i).getNomeFerramenta();
            }
        }
        return nome;
    }
}
