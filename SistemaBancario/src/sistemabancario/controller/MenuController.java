package sistemabancario.controller;

import sistemabancario.view.BotoesMenuView;
import sistemabancario.view.JanelaMenuView;

public class MenuController {
    private JanelaMenuView menuView;

    public MenuController(JanelaMenuView view) {
        this.menuView = view;
        initController();
    }
    
    //inicializa o controller e seta elementos
    private void initController(){
        this.menuView.setController(this);
        this.menuView.initView();
    }
    
    //troca visibilidade
    public void visibilidade(){
        this.menuView.desabilitaMenu(this);
    }

}
