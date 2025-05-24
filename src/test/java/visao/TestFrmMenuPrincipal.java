package visao;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;

public class TestFrmMenuPrincipal {
    
    @Test
    public void testMenuItemSairActionPerformed() throws Exception {
        //Cria a instancia da janela
        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        //Necessidade para testes em UI
        janela.setVisible(true);
        
        //Espera do UI para renderizar
        Thread.sleep(1000);
        
        //Acessar o JMenuBar e o JMenuItem
        JMenuBar menuBar = janela.getJMenuBar();
        
        JMenu menuArquivo = menuBar.getMenu(0);
        JMenuItem menuItemSair = menuArquivo.getItem(0);
        
        //Simulação do clique
        menuItemSair.doClick();
    }
    
    @Test
    public void testMenuItemGerenciarAmigosActionPerformed() throws Exception {
        //Criação da janela
        JFrame frame = new FrmMenuPrincipal();
        frame.setVisible(true);
        frame.setSize(400, 300);
        
        //Aquarda o swing processar os eventos
        Thread.sleep(500);
        
        JMenuBar menuBar = frame.getJMenuBar();
        assertNotNull(menuBar, "A JMenuBar deve estar presente na janela");
        
        JMenuItem itemGerenciar = null;
        
        
        //Percorre os menus dos itens em JMenuBar
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu == null) continue;
            
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null && "Gerenciar Amigos".equalsIgnoreCase(item.getText())) {
                    itemGerenciar = item;
                    break;
                }
            }
            if (itemGerenciar != null) break;
        }
        
        assertNotNull(itemGerenciar, "O item 'Gerenciar amigos' deve estar presente no menu.");
        
        //Simulação do clique
        itemGerenciar.doClick();
        
        //Aquarda a janela abrir
        Thread.sleep(500);
        
        //Verifica se FrmGerenciarAmigo foi aberto
        boolean encontrou = false;
        for (Window w : Window.getWindows()) {
            if (w instanceof FrmGerenciarAmigo && w.isVisible()) {
                encontrou = true;
                break;
            }
        }
        
        assertTrue(encontrou, "A janela FrmGerenciarAmigo deve estar visivel apos o clique.");
    }   
}
