package modelo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteFerramenta {
    
    Ferramenta ferramenta;
    
    @BeforeAll
    void inicializacao() {
        ferramenta = new Ferramenta(1, "Martelo", 10, "Bosh");
    }
    
    
    
    @AfterAll
    public void finalizacao() {
        ferramenta = null;
    }

}
