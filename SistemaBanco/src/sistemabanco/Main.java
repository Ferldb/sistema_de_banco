/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabanco;

import sistemabanco.controller.ClienteController;
import sistemabanco.model.dao.ClienteDao;
import sistemabanco.model.dao.ConnectionFactory;
import sistemabanco.view.ClienteView;

/**
 *
 * @author Franshinsca
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        ClienteView view = new ClienteView();
        ClienteDao modelDao = new ClienteDao(new ConnectionFactory());
        new ClienteController(view,modelDao);
        
    }
    
    
    
}
