/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.model;

public class ContaInvestimento extends Conta{

    private double montanteMin;
    private double depositoMin;

    public ContaInvestimento(long idconta, Cliente cliente, double saldo, double montanteMin, double depositoMin) {
        super(idconta, cliente, saldo);
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
    
    @Override
    public void remunera() {
        double novosaldo = getSaldo()*1.02;
        setSaldo(novosaldo);
    }
    
    @Override
    public boolean deposita(double valor) {
        if (valor < depositoMin) return false;
        double novosaldo = getSaldo()+valor;
        setSaldo(novosaldo);
        return true;
    }
    
    @Override
    public boolean saca(double valor) {
        if (valor < 0) return false;
        if((getSaldo() - valor) < (montanteMin)) return false;
        double novosaldo = getSaldo() - valor;
        setSaldo(novosaldo);
        return true;
    }
}
