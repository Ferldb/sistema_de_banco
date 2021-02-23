/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.model;

public class Conta {
    
    private long idconta;
    private Cliente cliente;
    private double saldo;
    private long tipoconta;

    public Conta(long idconta, Cliente cliente, double saldo) {
        this.idconta = idconta;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public long getIdconta() {
        return idconta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setIdconta(long idconta) {
        this.idconta = idconta;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public long getTipoconta() {
        return tipoconta;
    }

    public void setTipoconta(long tipoconta) {
        this.tipoconta = tipoconta;
    }
}
