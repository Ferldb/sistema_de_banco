package sistemabancario.controller;

import java.sql.SQLException;
import sistemabancario.model.Cliente;
import sistemabancario.model.Conta;
import sistemabancario.model.ContaCorrente;
import sistemabancario.model.ContaInvestimento;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.dao.ConnectionFactory;
import sistemabancario.model.dao.ContaDao;
import sistemabancario.view.JanelaManipularContaView;
import sistemabancario.view.JanelaMenuView;

public class ManipularController {

    private JanelaManipularContaView manipularView;
    private ClienteDao modelDao;
    private ContaDao contaDao;

    public ManipularController() {
        this.manipularView = new JanelaManipularContaView();
        this.modelDao = new ClienteDao(new ConnectionFactory());
        this.contaDao = new ContaDao(new ConnectionFactory());
        initController();
    }

    private void initController() {
        this.manipularView.setController(this);
        this.manipularView.initView();
    }

    //procura cliente por CPF e exibe nome na lacuna
    public void buscarCliente() {
        try {
            String cpf = manipularView.getCPF();
            if("".equals(cpf)){
                String s = "";
                manipularView.resultadoCpf(s);
                manipularView.mostrarMensagem("Digite um CPF para busca...");
            }
            else{
                Cliente cliente = modelDao.getCliente(cpf);
                String nome = cliente.getNome() + " " + cliente.getSobrenome();
                if (nome != null){
                    manipularView.resultadoCpf(nome);
                    if(manipularView.getlOperacao().isVisible())
                        manipularView.getlOperacao().setVisible(false);
                    if(manipularView.getbConfirmar().isVisible())
                        manipularView.getbConfirmar().setVisible(false);
                    if(manipularView.getCampoValor().isVisible())
                        manipularView.getCampoValor().setVisible(false);
                }
                else
                    throw new RuntimeException();
                manipularView.repaint();
            }
        } catch (Exception e) {
            String s = "";
            manipularView.resultadoCpf(s);
            manipularView.apresentaErro("Erro ao buscar cliente! " + e.getMessage());
        }
    }
    
    //verifica se o cliente buscado já possui conta vinculada ou não
    public int verificarCliente(){
        try{
            String cpf = manipularView.getCPF();
            Cliente cliente = modelDao.getCliente(cpf);
            contaDao.procuraCliente2(cliente.getId());
            return 0;
        }
        catch(Exception e){
            manipularView.apresentaErro("Não foi possível carregar os dados! " + e.getMessage());
        }
        return 1;
    }

    public void visibilidade() {
        this.manipularView.desabilitaMenu(this);
        JanelaMenuView view = new JanelaMenuView();
        new MenuController(view);
    }
    
    public void movimentarConta(int index){
        try{
            String cpf = manipularView.getCPF();
            Cliente cliente = modelDao.getCliente(cpf);
            Conta conta = contaDao.buscaConta(cliente);
            double v = 0.0;
            String valor = "";
            boolean sucesso;
            
            switch (index) {
                //saque
                case 0:
                    try{
                        valor = manipularView.getCampoValor().getText();
                        v = Double.parseDouble(valor);
                        try{
                            sucesso = conta.saca(v);
                            if (sucesso == true) {
                                contaDao.atualizaSaldo(conta, conta.getSaldo());
                                String s = String.format("%.2f", conta.getSaldo());
                                manipularView.mostrarMensagem("SAQUE realizado com sucesso!\nNOVO SALDO: R$ " + s);
                                this.movimentarConta(2);
                            } else {
                                manipularView.apresentaErro("Não foi possível completar o SAQUE!\nO valor a ser sacado não pode ser negativo!");
                            }
                            break;
                        }catch(Exception e){
                            manipularView.apresentaErro(e.getMessage());
                        }
                        break;
                    }catch(Exception e){
                        if ("".equals(valor))
                            throw new RuntimeException("Digite um valor para SAQUE!");
                        else
                            throw new RuntimeException("Valor de SAQUE inválido...");
                    }
                //depósito
                case 1:
                    try{
                        valor = manipularView.getCampoValor().getText();
                        v = Double.parseDouble(valor);
                        try{
                            sucesso = conta.deposita(v);
                            if (sucesso == true) {
                                contaDao.atualizaSaldo(conta, conta.getSaldo());
                                String s = String.format("%.2f", conta.getSaldo());
                                manipularView.mostrarMensagem("DEPÓSITO realizado com sucesso!\nNOVO SALDO: R$ " + s);
                                this.movimentarConta(2);
                            } else {
                                manipularView.apresentaErro("Não foi possível completar o DEPÓSITO!\nO valor a ser depositado não pode ser negativo!");
                            }
                            break;
                        }catch(Exception e){
                            manipularView.apresentaErro(e.getMessage());
                        }
                        break;
                    }catch(Exception e){
                        if ("".equals(valor))
                            throw new RuntimeException("Digite um valor para DEPÓSITO!");
                        else
                            throw new RuntimeException("Valor de DEPÓSITO inválido...");
                    }
                //saldo
                case 2:
                    double saldo = conta.getSaldo();
                    manipularView.mostrarSaldo(saldo);
                    break;
                //remuneração
                case 3:
                    conta.remunera();
                    contaDao.atualizaSaldo(conta, conta.getSaldo());
                    String s = String.format("%.2f", conta.getSaldo());
                    manipularView.mostrarMensagem("Remuneração realiada com sucesso!\nNOVO SALDO: R$ " + s);
                    this.movimentarConta(2);
                    break;
            }
        }
        catch(Exception e){
            manipularView.apresentaErro(e.getMessage());
        }
    }
    
}
