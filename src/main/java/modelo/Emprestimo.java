package modelo;

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
     * @param dataEmprestimo Quando foi emprestado a ferramenta.
     * @param dataDevolucao Data de quando a ferramenta deve ser devolvida.
     */
    public Emprestimo(int idEmprestimo, int idAmigo, int idFerramenta, String dataEmprestimo, String dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.idAmigo = idAmigo;
        this.idFerramenta = idFerramenta;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
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

    /**
     * Define a data do emprestimo.
     *
     * @param dataEmprestimo A data do emprestimo a ser definida.
     */
    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    /**
     * Obtém a data de devolução do emprestimo.
     *
     * @return A data de devolução do emprestimo.
     */
    public String getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * Define a data de devolução do emprestimo.
     *
     * @param dataDevolucao A data de devolução do emprestimo a ser definida.
     */
    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * Retorna uma lista de emprestimos.
     *
     * @return Uma lista de emprestimos.
     */
    
}
