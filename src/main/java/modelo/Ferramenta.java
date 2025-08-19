package modelo;

/**
 * Classe de ferramenta com nome, marca, custo e disponibilidade.
 */
public class Ferramenta {

    private int idFerramenta;
    private String nomeFerramenta;
    private String marcaFerramenta;
    private double custoFerramenta;

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

}
