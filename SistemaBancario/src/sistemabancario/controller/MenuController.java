package sistemabancario.controller;

import sistemabancario.view.BotoesMenuView;
import sistemabancario.view.JanelaMenuView;

public class MenuController {
    private JanelaMenuView menuView;

    public MenuController(JanelaMenuView view) {
        this.menuView = view;
        initController();
    }
    
    private void initController(){
        this.menuView.setController();
        this.menuView.initView();
    }
}
