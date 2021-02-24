/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import sistemabancario.model.Cliente;
import sistemabancario.model.Conta;
import sistemabancario.model.ContaCorrente;
import sistemabancario.model.ContaInvestimento;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.dao.ConnectionFactory;
import sistemabancario.model.dao.ContaDao;
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
    private ClienteDao clienteDao;
    private ContaDao contaDao;
    
    public ContaController(){
        this.contaView = new JanelaContaView();
        this.clienteDao = new ClienteDao(new ConnectionFactory());
        this.contaDao = new ContaDao(new ConnectionFactory());
        initController();
    }
    
    private void initController(){
        this.contaView.setController(this);
        this.contaView.initView();
    }
    
    public void buscarCliente(){
        try{
            String cpf = contaView.getCPF();
            if("".equals(cpf)){
                String s = "";
                contaView.resultadoCpf(s);
                contaView.mostrarMensagem("Digite um CPF para busca...");
            }
            else {
                Cliente cliente = clienteDao.getCliente(cpf);
                String nome = "Cliente: " + cliente.getNome() + " " + cliente.getSobrenome();
                if (nome != null)
                    contaView.resultadoCpf(nome);
                else
                    throw new RuntimeException();
                contaView.repaint();
            }
        }
        catch(Exception e){
            String s = "";
            contaView.resultadoCpf(s);
            contaView.apresentaErro("Erro ao buscar cliente! " + e.getMessage());
        }
    }

    public void visibilidade() {
        this.contaView.desabilitaMenu(this);
        JanelaMenuView view = new JanelaMenuView();
        new MenuController(view);
    }
    
    public void verificaCliente(){
        
    }
    
    //tipo = 1 conta corrente  -  tipo = 2 conta investimento
    public void criarConta(int tipo) {
        try{
            String cpf = contaView.getCPF();
            Cliente cliente = clienteDao.getCliente(cpf);

            int x = contaDao.procuraCliente(cliente.getId());        //procura cliente na tabela de contas
            if (x == 0){
                //significa que cliente ainda não pussui conta
                if (tipo == 1){
                    ContaCorrente cc = contaView.getContaCorrente();   //pega os dados inseridos
                    cc.setCliente(cliente);                            //seta id do cliente
                    Double d = contaView.getDepositoCC();              //pega saldo inicial
                    cc.setSaldo(cc.getSaldo() + d);                    //seta saldo
                    contaDao.inserirCC(cc);
                    contaView.limparFormulario(tipo);
                    contaView.mostrarMensagem("Conta Corrente criada com sucesso para o(a) cliente "+cliente.getNome()+" "+cliente.getSobrenome()+"\n Num conta = "+cc.getIdconta());

                }
                else if (tipo == 2){
                    ContaInvestimento ci = contaView.getContaInvestimento(); //pega os dados inseridos
                    ci.setCliente(cliente);                                  //seta id cliente
                    Double d = contaView.getDepositoCI();                    //pega saldo inicial
                    ci.setSaldo(ci.getSaldo() + d);                          //seta saldo
                    contaDao.inserirCI(ci);
                    contaView.limparFormulario(tipo);
                    contaView.mostrarMensagem("Conta Investimento criada com sucesso para o(a) cliente "+cliente.getNome()+" "+cliente.getSobrenome()+"\n Num conta = "+ci.getIdconta());
                }
            }
        } catch(Exception e){
            contaView.apresentaErro("Não foi possível criar conta! " + e.getMessage());
        }
    }

   
    
    
         
    
}
