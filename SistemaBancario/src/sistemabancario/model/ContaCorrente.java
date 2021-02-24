/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.model;

public class ContaCorrente extends Conta implements ContaI {

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

    @Override
    public void remunera() {
        double novosaldo = getSaldo() * 1.01;
        setSaldo(novosaldo);
    }
    
    @Override
    public boolean saca(double valor) {
        if (valor < 0) return false;
        if((getSaldo()-valor) < (0-limite)) return false;
        double novosaldo = getSaldo() - valor;
        setSaldo(novosaldo);
        return true;
    }
}
