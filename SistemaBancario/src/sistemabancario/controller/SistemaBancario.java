package sistemabancario.controller;

import sistemabancario.controller.MenuController;
import sistemabancario.view.BotoesMenuView;
import sistemabancario.view.JanelaMenuView;

public class SistemaBancario {

    public static void main(String[] args) {
        //inicializa menu
        JanelaMenuView view = new JanelaMenuView();
        new MenuController(view);
    }
    
}