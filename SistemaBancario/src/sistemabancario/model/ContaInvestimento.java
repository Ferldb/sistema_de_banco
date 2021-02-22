/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.model;

public class ContaInvestimento extends Conta {
    
    private double montanteMin;
    private double depositoMin;

    public ContaInvestimento(long idconta, long idcliente, double saldo, double montanteMin, double depositoMin) {
        super(idconta, idcliente, saldo);
        this.montanteMin = montanteMin;
        this.depositoMin = depositoMin;
    }
    
    public double getMontanteMin() {
        return montanteMin;
    }

    public double getDepositoMin() {
        return depositoMin;
    }

      public void setMontanteMin(double montanteMin) {
        this.montanteMin = montanteMin;
    }

    public void setDepositoMin(double depositoMin) {
        this.depositoMin = depositoMin;
    }


    /*
    
    //Recebe como parâmetro o valor a ser depositado. Se o valor a ser depositado for maior ou igual ao depositoMinimo 
    //então, o depósito deve ser efetuado. Para isso chame o método deposita da classe pai (Conta) e retorne true. Caso contrário, 
    //deve-se retornar false. Mostrar mensagem na tela informando usuário.
    public boolean deposita(double valor){
    }
    
    //Recebe como parâmetro o valor a ser sacado. Se o novo valor do saldo (considerando o saque) for maior ou igual ao montanteMinimo,
    //o saque deve ser efetuado. Para isso invoque o método saque da classe pai (Conta) e retorne true. Caso contrário, deve-se retornar 
    //false. Mostrar mensagem na tela informando usuário.
    public boolean saca(double valor){
    }
    
    //Aplicar remuneração de 2% ao saldo da conta
    public void remunera(){
    }
    
    */
}
