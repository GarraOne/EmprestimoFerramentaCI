package dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DAOExceptionTest {
    
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
    

