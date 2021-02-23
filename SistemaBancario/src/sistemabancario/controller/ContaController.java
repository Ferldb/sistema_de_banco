/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import sistemabancario.model.Cliente;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.dao.ConnectionFactory;
import sistemabancario.view.JanelaContaView;
import sistemabancario.view.JanelaMenuView;
import sistemabancario.view.PainelContaCorrente;
import sistemabancario.view.PainelContaInvestimento;

/**
 *
 * @author Franshinsca
 */
public class ContaController {
    private JanelaContaView contaView;
    private ClienteDao modelDao;
    
    public ContaController(){
        this.contaView = new JanelaContaView();
        this.modelDao = new ClienteDao(new ConnectionFactory());
        initController();
    }
    
    private void initController(){
        this.contaView.setController(this);
        this.contaView.initView();
    }
    
    public void buscarCliente(){
        try{
            String cpf = contaView.getCPF();
            Cliente cliente = modelDao.getCliente(cpf);
            String nome = "Nome do cliente: "+cliente.getNome()+" "+cliente.getSobrenome();
            if (nome!=null) contaView.resultadoCpf(nome);
            else throw new RuntimeException();

    contaView.repaint();
        }
        catch(Exception e){
            contaView.apresentaErro("Contato não encontrado.");
        }
    }

    public void visibilidade() {
        this.contaView.desabilitaMenu(this);
        JanelaMenuView view = new JanelaMenuView();
        new MenuController(view);
    }

   
    
    
         
    
}
