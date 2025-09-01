package service;

import dao.EmprestimoDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Emprestimo;

public class EmprestimoService {

    private static final Logger logger = Logger.getLogger(EmprestimoService.class.getName());

    private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

    public List<Emprestimo> listaEmprestimo() {
        return emprestimoDAO.getListaEmprestimo();
    }

    /**
     * Insere um emprestimo no banco de dados.
     *
     * @param idAmigo O id do amigo a ser inserido.
     * @param idFerramenta O id da ferramenta a ser inserido.
     * @param dataEmprestimo Data do emprestimo a ser inserido.
     * @return true se a inserção for bem-sucedida, false caso contrário.
     */
    public boolean insertEmprestimoDB(int idAmigo, int idFerramenta, String dataEmprestimo) {
        int maiorID = emprestimoDAO.maiorIDEmprestimo() + 1;
        Emprestimo emprestimo = new Emprestimo(maiorID, idAmigo, idFerramenta, dataEmprestimo, "");
        emprestimoDAO.insertEmprestimoDB(emprestimo);
        return true;

    }

    /**
     * Remove um emprestimo do banco de dados.
     *
     * @param idEmprestimo O ID do emprestimo a ser removido.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */
    public boolean deleteEmprestimoDB(int idEmprestimo) {
        emprestimoDAO.deleteEmprestimoDB(idEmprestimo);
        return true;
    }

    /**
     * Atualiza um emprestimo no banco de dados.
     *
     * @param idEmprestimo O id do emprestimo a ser atualizado.
     * @param idAmigo O id do amigo a ser atualizado.
     * @param idFerramenta O id da ferramenta a ser atualizada.
     * @param dataEmprestimo A Data do emprestimo a ser atualizada.
     * @param dataDevolucao A Data de devolução da ferramenta a ser atualizada.
     * @return true se a inserção for bem-sucedida, false caso contrário.
     */
    public boolean updateEmprestimoDB(int idEmprestimo, int idAmigo, int idFerramenta, String dataEmprestimo, String dataDevolucao) {
        logger.log(Level.INFO, "Data de devolução recebida: {0}", dataDevolucao);
        Emprestimo emprestimo = new Emprestimo(idEmprestimo, idAmigo, idFerramenta, dataEmprestimo, dataDevolucao);
        logger.log(Level.INFO, "Data de devolução do objeto emprestimo: {0}", emprestimo.getDataDevolucao());
        emprestimoDAO.updateEmprestimoDB(emprestimo);
        return true;
    }

    /**
     * Retorna um emprestimo do banco de dados com base no ID.
     *
     * @param idEmprestimo O ID do Emprestimo a ser recuperado.
     * @return O emprestimo recuperado do banco de dados.
     */
    public Emprestimo retrieveEmprestimoDB(int idEmprestimo) {
        return emprestimoDAO.retrieveEmprestimoDB(idEmprestimo);
    }

    /**
     * Retorna o maior ID de emprestimo no banco de dados.
     *
     * @return O maior ID de emprestimo no banco de dados.
     */
    public int maiorID() {
        return emprestimoDAO.maiorIDEmprestimo();
    }

    public List<Emprestimo> getListaEmprestimoAtivo() {
        ArrayList<Emprestimo> listaEmprestimoAtivo = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Emprestimo> listaEmprestimo = this.listaEmprestimo();

            for (int i = 0; i < listaEmprestimo.size(); i++) {

                if ((!"null".equals(listaEmprestimo.get(i).getDataDevolucao())) && (!"".equals(listaEmprestimo.get(i).getDataDevolucao()))) {
                    String[] dataDevolucaoInvertida = listaEmprestimo.get(i).getDataDevolucao().split("-");
                    Date dataDevolucaoInvertido = sdf.parse(dataDevolucaoInvertida[2] + "-" + dataDevolucaoInvertida[1] + "-" + dataDevolucaoInvertida[0]);
                    Date dataAtual = sdf.parse(LocalDate.now() + "");
                    if (dataAtual.compareTo(dataDevolucaoInvertido) < 0) {
                        listaEmprestimoAtivo.add(listaEmprestimo.get(i));

                    }
                } else {
                    listaEmprestimoAtivo.add(listaEmprestimo.get(i));
                }

            }
        } catch (ParseException erro) {
            logger.log(Level.SEVERE, "Erro: formato da data inválido", erro);
        }

        return listaEmprestimoAtivo;
    }

    public String emprestimoAtivo(int idEmprestimo) {
        String ativo = "Não";
        EmprestimoService emp = new EmprestimoService();
        List<Emprestimo> listaEmprestimoAtivo = emp.getListaEmprestimoAtivo();
        for (int i = 0; i < listaEmprestimoAtivo.size(); i++) {
            if (listaEmprestimoAtivo.get(i).getIDEmprestimo() == idEmprestimo) {
                ativo = "Sim";
            }
        }
        return ativo;
    }
}
