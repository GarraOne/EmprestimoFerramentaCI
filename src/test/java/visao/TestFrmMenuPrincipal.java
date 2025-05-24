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
    
    @Test
    public void testMenuItemCadastarFerramentasActionPerformed() throws Exception {
        JFrame frame = new FrmMenuPrincipal();
        frame.setVisible(true);
        frame.setSize(400, 300);
        
        Thread.sleep(1000);
        
        JMenuBar menuBar = frame.getJMenuBar();
        assertNotNull(menuBar, "A JMenuBar deve estar presente na janela");
        
        JMenuItem itemCadastrarFerramentas = null;
        
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu == null) continue;
            
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null && "Cadastrar Ferramentas".equalsIgnoreCase(item.getText())) {
                    itemCadastrarFerramentas = item;
                    break;
                }
            }
            if (itemCadastrarFerramentas != null) break;
        }
        assertNotNull(itemCadastrarFerramentas, "O item 'Cadastrar Ferramentas' deve estar presente no menu no menu.");
        
        itemCadastrarFerramentas.doClick();
        
        Thread.sleep(500);
        
        boolean encontrou = false;
        for(Window w: Window.getWindows()) {
            if (w instanceof FrmCadastroFerramenta && w.isVisible()) {
                encontrou = true;
                break;
            }
        }
        
        assertTrue(encontrou, "A janela FrmCadastroFerramenta deve estar visivel apos o clique.");
    }
    
    @Test
    public void testMenuItemEmpréstimoActionPerformed() throws Exception {
        JFrame frame = new FrmMenuPrincipal();
        frame.setVisible(true);
        frame.setSize(400, 300);
        
        Thread.sleep(1000);
        
        JMenuBar menuBar = frame.getJMenuBar();
        assertNotNull(menuBar, "A JMenuBar deve estar presente na janela");
        
        JMenuItem itemEmprestimo = null;
        
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu == null) continue;
            
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null && "Empréstimo".equalsIgnoreCase(item.getText())) {
                    itemEmprestimo = item;
                    break;
                }
            }
            if (itemEmprestimo != null) break;
        }
        assertNotNull(itemEmprestimo, "O item 'Empréstimo' deve estar presente no menu no menu.");
        
        itemEmprestimo.doClick();
        
        Thread.sleep(500);
        
        boolean encontrou = false;
        for(Window w: Window.getWindows()) {
            if (w instanceof FrmCadastroEmprestimo && w.isVisible()) {
                encontrou = true;
                break;
            }
        }
        
        assertTrue(encontrou, "A janela FrmCadastroEmprestimo deve estar visivel apos o clique.");
    }
    
    @Test
    public void testMenuItemDevoluçãoActionPerformed() throws Exception {
        JFrame frame = new FrmMenuPrincipal();
        frame.setVisible(true);
        frame.setSize(400, 300);
        
        Thread.sleep(1000);
        
        JMenuBar menuBar = frame.getJMenuBar();
        assertNotNull(menuBar, "A JMenuBar deve estar presente na janela");
        
        JMenuItem itemDevolucao = null;
        
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu == null) continue;
            
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null && "Devolução".equalsIgnoreCase(item.getText())) {
                    itemDevolucao = item;
                    break;
                }
            }
            if (itemDevolucao != null) break;
        }
        assertNotNull(itemDevolucao, "O item 'Devolução' deve estar presente no menu no menu.");
        
        itemDevolucao.doClick();
        
        Thread.sleep(500);
        
        boolean encontrou = false;
        for(Window w: Window.getWindows()) {
            if (w instanceof FrmCadastroDevolucao && w.isVisible()) {
                encontrou = true;
                break;
            }
        }
        
        assertTrue(encontrou, "A janela FrmCadastroDevolucao deve estar visivel apos o clique.");
    }
    
    @Test
    public void testMenuItemGerenciarEmprestimoPerformed() throws Exception {
        JFrame frame = new FrmMenuPrincipal();
        frame.setVisible(true);
        frame.setSize(400, 300);
        
        Thread.sleep(1000);
        
        JMenuBar menuBar = frame.getJMenuBar();
        assertNotNull(menuBar, "A JMenuBar deve estar presente na janela");
        
        JMenuItem itemGerenciarEmprestimo = null;
        
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu == null) continue;
            
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null && "Gerenciar Empréstimo".equalsIgnoreCase(item.getText())) {
                    itemGerenciarEmprestimo = item;
                    break;
                }
            }
            if (itemGerenciarEmprestimo != null) break;
        }
        assertNotNull(itemGerenciarEmprestimo, "O item 'Gerenciar Empréstimo' deve estar presente no menu no menu.");
        
        itemGerenciarEmprestimo.doClick();
        
        Thread.sleep(500);
        
        boolean encontrou = false;
        for(Window w: Window.getWindows()) {
            if (w instanceof FrmGerenciarEmprestimo && w.isVisible()) {
                encontrou = true;
                break;
            }
        }
        
        assertTrue(encontrou, "A janela FrmGerenciarEmprestimo deve estar visivel apos o clique.");
    }
    
    @Test
    public void testMenuItemRelatorioPerformed() throws Exception {
        JFrame frame = new FrmMenuPrincipal();
        frame.setVisible(true);
        frame.setSize(400, 300);
        
        Thread.sleep(1000);
        
        JMenuBar menuBar = frame.getJMenuBar();
        assertNotNull(menuBar, "A JMenuBar deve estar presente na janela");
        
        JMenuItem itemRelatorio = null;
        
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu == null) continue;
            
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null && "Relatório".equalsIgnoreCase(item.getText())) {
                    itemRelatorio = item;
                    break;
                }
            }
            if (itemRelatorio != null) break;
        }
        assertNotNull(itemRelatorio, "O item 'Relatório' deve estar presente no menu no menu.");
        
        itemRelatorio.doClick();
        
        Thread.sleep(500);
        
        boolean encontrou = false;
        for(Window w: Window.getWindows()) {
            if (w instanceof FrmRelatorio && w.isVisible()) {
                encontrou = true;
                break;
            }
        }
        
        assertTrue(encontrou, "A janela FrmRelatorio deve estar visivel apos o clique.");
    }
    
    @Test
    public void testMenuItemCadastrarAmigosPerformed() throws Exception {
        JFrame frame = new FrmMenuPrincipal();
        frame.setVisible(true);
        frame.setSize(400, 300);
        
        Thread.sleep(1000);
        
        JMenuBar menuBar = frame.getJMenuBar();
        assertNotNull(menuBar, "A JMenuBar deve estar presente na janela");
        
        JMenuItem itemCadastrarAmigos = null;
        
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu == null) continue;
            
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null && "Cadastrar Amigos".equalsIgnoreCase(item.getText())) {
                    itemCadastrarAmigos = item;
                    break;
                }
            }
            if (itemCadastrarAmigos != null) break;
        }
        assertNotNull(itemCadastrarAmigos, "O item 'Cadastrar Amigos' deve estar presente no menu no menu.");
        
        itemCadastrarAmigos.doClick();
        
        Thread.sleep(500);
        
        boolean encontrou = false;
        for(Window w: Window.getWindows()) {
            if (w instanceof FrmCadastroAmigo && w.isVisible()) {
                encontrou = true;
                break;
            }
        }
        
        assertTrue(encontrou, "A janela FrmCadastroAmigo deve estar visivel apos o clique.");
    }
    
    @Test
    public void testMenuItemGerenciarFerramentasAmigosPerformed() throws Exception {
        JFrame frame = new FrmMenuPrincipal();
        frame.setVisible(true);
        frame.setSize(400, 300);
        
        Thread.sleep(1000);
        
        JMenuBar menuBar = frame.getJMenuBar();
        assertNotNull(menuBar, "A JMenuBar deve estar presente na janela");
        
        JMenuItem itemGerenciarFerramentas = null;
        
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu == null) continue;
            
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null && "Gerenciar Ferramentas".equalsIgnoreCase(item.getText())) {
                    itemGerenciarFerramentas = item;
                    break;
                }
            }
            if (itemGerenciarFerramentas != null) break;
        }
        assertNotNull(itemGerenciarFerramentas, "O item 'Gerenciar Ferramentas' deve estar presente no menu no menu.");
        
        itemGerenciarFerramentas.doClick();
        
        Thread.sleep(500);
        
        boolean encontrou = false;
        for(Window w: Window.getWindows()) {
            if (w instanceof FrmGerenciarFerramenta && w.isVisible()) {
                encontrou = true;
                break;
            }
        }
        
        assertTrue(encontrou, "A janela FrmGerenciarFerramenta deve estar visivel apos o clique.");
    }
}
