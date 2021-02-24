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
    
    public void remunera() {
        if (this.getSaldo() < 0) throw new RuntimeException("Impossível realizar remuneração em saldo negativo!! Saldo: "+this.getSaldo());
        double novosaldo = this.getSaldo()*1.02;
        this.setSaldo(novosaldo);
    }
    
    public boolean deposita(double valor) {
        if (valor < this.getDepositoMin()) throw new RuntimeException("Impossivel realizar o depósito!! O valor depositado deve ser maior ou igual ao Depósito Mínimo estipulado para a conta");
        else{
            boolean deposito = super.deposita(valor);
            if(!deposito) return false;
            else return true;
        }
    }
    
    public boolean saca(double valor) {
        if((this.getSaldo() - valor) < (this.getMontanteMin())) throw new RuntimeException("Impossivel realizar o saque! O saldo não pode ficar menor do que o Montante Mínimo estipulado para a conta");
        else{
            boolean saque = super.saca(valor);
            if(!saque) return false;
            else return true;
        }
    }
}