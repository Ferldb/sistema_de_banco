package sistemabancario;

import sistemabancario.controller.MenuController;
import sistemabancario.view.BotoesMenuView;
import sistemabancario.view.JanelaMenuView;

public class SistemaBancario {

    public static void main(String[] args) {
        JanelaMenuView view = new JanelaMenuView();
        new MenuController(view);
    }
    
}
