/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.model;


public class ContaCorrente extends Conta{

    private double limite;

    public ContaCorrente(double limite, long idconta, Cliente cliente, double saldo) {
        super(idconta, cliente, saldo);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
    
    /*
    
    //Antes de efetuar o saque deve-se verificar se o valor sacado não ultrapassa o limite da conta. 
    //Ou seja, a conta poderá ficar negativa até o limite estipulado na sua criação. Mostrar mensagem na tela informando o usuário.
    public boolean saca(double valor){
    }
    
    //Aplicar remuneração de 1% ao saldo da conta.
    public void remunera(){
    }
    
    */

    
}
