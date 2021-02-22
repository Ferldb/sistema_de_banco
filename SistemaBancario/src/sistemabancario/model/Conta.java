/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.model;

public class Conta {
    
    private long idconta;
    private long idcliente;
    private double saldo;

    public Conta(long idconta, long idcliente, double saldo) {
        this.idconta = idconta;
        this.idcliente = idcliente;
        this.saldo = saldo;
    }

    public long getIdconta() {
        return idconta;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setIdconta(long idconta) {
        this.idconta = idconta;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
    /*
    //o valor depositado deve ser positivo. Caso contrário o método retorna false
    public boolean deposita(double valor){
    }
    
    //o valor sacado deve ser positivo. Caso contrário o método retorna false. Mostrar mensagem na tela informando usuário
    public boolean saca(double valor){
    }
    */
}
