package modelo;

/**
 * Representa um amigo com id, nome e telefone.
 */
public class Amigo {

    private int idAmigo;
    private String nomeAmigo;
    private String telefone;

    /**
     * Construtor padrão para a classe Amigo. Inicializa o amigo com id, nome e
     * telefone vazios.
     */
    public Amigo() {
        this(0, "", "");
    }

    /**
     * Construtor para a classe Amigo. Inicializa o amigo com o id, nome e
     * telefone fornecidos.
     *
     * @param idAmigo O id do amigo
     * @param nomeAmigo O nome do amigo.
     * @param telefone O telefone do amigo.
     */
    public Amigo(int idAmigo, String nomeAmigo, String telefone) {
        this.idAmigo = idAmigo;
        this.nomeAmigo = nomeAmigo;
        this.telefone = telefone;
    }

    /**
     * Obtém o nome do amigo.
     *
     * @return O nome do amigo.
     */
    public String getNomeAmigo() {
        return nomeAmigo;
    }

    /**
     * Define o nome do amigo.
     *
     * @param nomeAmigo O nome do amigo a ser definido.
     */
    public void setNomeAmigo(String nomeAmigo) {
        this.nomeAmigo = nomeAmigo;
    }

    /**
     * Obtém o telefone do amigo.
     *
     * @return O telefone do amigo.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do amigo.
     *
     * @param telefone O telefone do amigo a ser definido.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém o ID do amigo.
     *
     * @return O ID do amigo.
     */
    public int getIdAmigo() {
        return idAmigo;
    }

    /**
     * Define o ID do amigo.
     *
     * @param idAmigo O ID do amigo a ser definido.
     */
    public void setIdAmigo(int idAmigo) {
        this.idAmigo = idAmigo;
    }

}
