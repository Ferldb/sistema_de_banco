/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.model;

public class Conta implements ContaI {

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

    //o valor depositado deve ser positivo. Caso contrário o método retorna false

    public long getTipoconta() {
        return tipoconta;
    }

    public void setTipoconta(long tipoconta) {
        this.tipoconta = tipoconta;
    }

    @Override
    public Cliente getDono() {
        return this.cliente;
    }

    @Override
    public int getNumero() {
        return (int) this.idconta;
    }

    @Override
    public void remunera() {
        double novosaldo = getSaldo() * 1;
        setSaldo(novosaldo);
    }

    @Override
    public boolean deposita(double valor) {
        if (valor < 0) return false;
        this.saldo = saldo + valor;
        return true;
    }

    @Override
    public boolean saca(double valor) {
        if (valor < 0) return false;
        this.saldo = saldo - valor;
        return true;
    }
}
