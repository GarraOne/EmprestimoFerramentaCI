package dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DAOExceptionTest {
    
   @Test
    void testConstrutorComMensagem() {
        String msg = "Erro ao acessar o banco de dados";
        DAOException ex = new DAOException(msg);

        assertEquals(msg, ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    void testConstrutorComMensagemECausa() {
        String msg = "Erro ao salvar dados";
        Throwable causa = new NullPointerException("Objeto nulo");
        DAOException ex = new DAOException(msg, causa);

        assertEquals(msg, ex.getMessage());
        assertEquals(causa, ex.getCause());
    }
}
    

