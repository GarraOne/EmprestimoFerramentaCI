package modelo;

import dao.EmprestimoDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa um emprestimo com id, id do amigo, id da ferramenta, data de
 * emprestimo e data de devolução.
 */
public class Emprestimo {

    private int idEmprestimo;
    private int idAmigo;
    private int idFerramenta;
    private String dataEmprestimo;
    private String dataDevolucao;
    private EmprestimoDAO dao;

    /**
     * Construtor padrão para a classe Emprestimo. Inicializa o id do
     * emprestimo, o id do amigo(para quem foi emprestado), o id da
     * ferramenta(ferramenta que foi emprestada), data do emprestimo e data de
     * devolução vazios.
     */
    public Emprestimo() {
        this(0, 0, 0, "", "");
    }

    /**
     * Construtor para a classe Emprestimo. Inicializa o emprestimo com os id's
     * de Emprestimo, Amigo e ferramenta, Data de emprestimo e de devolução
     * fornecidos.
     *
     * @param idEmprestimo Id do emprestimo
     * @param idAmigo Para que amigo foi emprestada a ferramenta.
     * @param idFerramenta Que ferramenta foi emprestada.
     * @param DataEmprestimo Quando foi emprestado a ferramenta.
     * @param DataDevolucao Data de quando a ferramenta deve ser devolvida.
     */
    public Emprestimo(int idEmprestimo, int idAmigo, int idFerramenta, String DataEmprestimo, String DataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.idAmigo = idAmigo;
        this.idFerramenta = idFerramenta;
        this.dataEmprestimo = DataEmprestimo;
        this.dataDevolucao = DataDevolucao;
        this.dao = new EmprestimoDAO();
    }

    /**
     * Obtém od id do emprestimo.
     *
     * @return o id do emprestimo.
     *
     */
    public int getIDEmprestimo() {
        return idEmprestimo;
    }

    /**
     * Define o id do emprestimo.
     *
     * @param idEmprestimo O id do emprestimo a ser definido.
     */
    public void setIDEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    /**
     * Obtém para que amigo foi emprestado.
     *
     * @return para quem foi emprestado.
     *
     */
    public int getIDAmigo() {
        return idAmigo;
    }

    /**
     * Define para quem foi emprestado.
     *
     * @param idAmigo O id do amigo a ser definido.
     */
    public void setIDAmigo(int idAmigo) {
        this.idAmigo = idAmigo;
    }

    /**
     * Obtém od id da ferramenta.
     *
     * @return o id da ferramenta.
     *
     */
    public int getIDFerramenta() {
        return idFerramenta;
    }

    /**
     * Define o id da ferramenta.
     *
     * @param idFerramenta O id da ferramenta a ser definido.
     */
    public void setIDFerramenta(int idFerramenta) {
        this.idFerramenta = idFerramenta;
    }

    /**
     * Obtém a data do emprestimo.
     *
     * @return A data do emprestimo.
     */
    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public String getDataEmprestimoInvertido() {
        String dataEmprestimo = this.dataEmprestimo;
        if (dataEmprestimo != null) {
            String[] DataEmprestimoSeparado = this.dataEmprestimo.split("-");
            dataEmprestimo = DataEmprestimoSeparado[2] + "-" + DataEmprestimoSeparado[1] + "-" + DataEmprestimoSeparado[0];
        }
        return dataEmprestimo;
    }

    /**
     * Define a data do emprestimo.
     *
     * @param DataEmprestimo A data do emprestimo a ser definida.
     */
    public void setDataEmprestimo(String DataEmprestimo) {
        this.dataEmprestimo = DataEmprestimo;
    }

    /**
     * Obtém a data de devolução do emprestimo.
     *
     * @return A data de devolução do emprestimo.
     */
    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public String getDataDevolucaoInvertido() {
        String dataDevolucao = this.dataDevolucao;
        if (dataDevolucao != null) {
            String[] DataDevolucaoSeparado = this.dataDevolucao.split("-");
            dataDevolucao = DataDevolucaoSeparado[2] + "-" + DataDevolucaoSeparado[1] + "-" + DataDevolucaoSeparado[0];
        }
        return dataDevolucao;
    }

    /**
     * Define a data de devolução do emprestimo.
     *
     * @param DataDevolucao A data de devolução do emprestimo a ser definida.
     */
    public void setDataDevolucao(String DataDevolucao) {
        this.dataDevolucao = DataDevolucao;
    }

    /**
     * Retorna uma lista de emprestimos.
     *
     * @return Uma lista de emprestimos.
     */
    public ArrayList<Emprestimo> listaEmprestimo() {
        return dao.getListaEmprestimo();
    }

    /**
     * Insere um emprestimo no banco de dados.
     *
     * @param idAmigo O id do amigo a ser inserido.
     * @param idFerramenta O id da ferramenta a ser inserido.
     * @param DataEmprestimo Data do emprestimo a ser inserido.
     * @param DataDevolucao Data de devolução da ferramenta a ser inserida.
     * @return true se a inserção for bem-sucedida, false caso contrário.
     */
    public boolean insertEmprestimoDB(int idAmigo, int idFerramenta, String dataEmprestimo) {
        int maiorID = dao.maiorIDEmprestimo() + 1;
        Emprestimo emprestimo = new Emprestimo(maiorID, idAmigo, idFerramenta, dataEmprestimo, null);
        dao.insertEmprestimoDB(emprestimo);
        return true;

    }

    /**
     * Remove um emprestimo do banco de dados.
     *
     * @param idEmprestimo O ID do emprestimo a ser removido.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */
    public boolean deleteEmprestimoDB(int idEmprestimo) {
        dao.deleteEmprestimoDB(idEmprestimo);
        return true;
    }

    /**
     * Retorna o índice de um emprestimo na lista.
     *
     * @param idEmprestimo O ID do emprestimo a ser buscado.
     * @return O índice do emprestimo na lista.
     */
    private int procuraIndice(int idEmprestimo) {
        int indice = -1;
        for (int i = 0; i < EmprestimoDAO.listaEmprestimo.size(); i++) {
            if (EmprestimoDAO.listaEmprestimo.get(i).getIDEmprestimo() == idEmprestimo) {
                indice = i;
            }
        }
        return indice;
    }

    /**
     * Atualiza um emprestimo no banco de dados.
     *
     * @param idEmprestimo O id do emprestimo a ser atualizado.
     * @param idAmgio O id do amigo a ser atualizado.
     * @param idFerramenta O id da ferramenta a ser atualizada.
     * @param DataEmprestimo A Data do emprestimo a ser atualizada.
     * @param DataDevolucao A Data de devolução da ferramenta a ser atualizada.
     * @return true se a inserção for bem-sucedida, false caso contrário.
     */
    public boolean updateEmprestimoDB(int idEmprestimo, int idAmigo, int idFerramenta, String dataEmprestimo, String dataDevolucao) {
        Emprestimo emprestimo = new Emprestimo(idEmprestimo, idAmigo, idFerramenta, dataEmprestimo, dataDevolucao);
        int indice = this.procuraIndice(idEmprestimo);
        dao.updateEmprestimoDB(emprestimo);
        return true;
    }

    /**
     * Retorna um emprestimo do banco de dados com base no ID.
     *
     * @param idEmprestimo O ID do Emprestimo a ser recuperado.
     * @return O emprestimo recuperado do banco de dados.
     */
    public Emprestimo retrieveEmprestimoDB(int idEmprestimo) {
        return dao.retrieveEmprestimoDB(idEmprestimo);
    }

    /**
     * Retorna o maior ID de emprestimo no banco de dados.
     *
     * @return O maior ID de emprestimo no banco de dados.
     */
    public int maiorID() {
        return dao.maiorIDEmprestimo();
    }

    public ArrayList<Emprestimo> getListaEmprestimoAtivo() {
        ArrayList<Emprestimo> listaEmprestimoAtivo = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ArrayList<Emprestimo> listaEmprestimo = this.listaEmprestimo();

            for (int i = 0; i < listaEmprestimo.size(); i++) {

                if (listaEmprestimo.get(i).getDataDevolucao() == null) {
                    listaEmprestimoAtivo.add(listaEmprestimo.get(i));

                }
                if (listaEmprestimo.get(i).getDataDevolucao() != null) {
                    Date dataDevolucao = sdf.parse(listaEmprestimo.get(i).getDataDevolucao());
                    Date dataAtual = sdf.parse(LocalDate.now() + "");

                    if (dataAtual.compareTo(dataDevolucao) < 0) {
                        listaEmprestimoAtivo.add(listaEmprestimo.get(i));

                    }
                }

            }
        } catch (ParseException erro) {
        }

        return listaEmprestimoAtivo;
    }

    public String emprestimoAtivo(int idEmprestimo) {
        String ativo = "Não";
        Emprestimo emp = new Emprestimo();
        ArrayList<Emprestimo> listaEmprestimoAtivo = emp.getListaEmprestimoAtivo();
        for (int i = 0; i < listaEmprestimoAtivo.size(); i++) {
            if (listaEmprestimoAtivo.get(i).getIDEmprestimo() == idEmprestimo) {
                ativo = "Sim";
            }
        }
        return ativo;
    }
}
