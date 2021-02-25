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
    
    public boolean deposita(double valor) {
        if (valor < this.getDepositoMin())
            throw new RuntimeException("Não foi possível completar o DEPÓSITO!\nO valor depositado deve ser maior ou igual ao DEPÓSITO MÍNIMO estipulado para a conta...\n*DEPÓSITO MÍNIMO: " + String.format("%.2f", this.getDepositoMin()));
        else{
            boolean deposito = super.deposita(valor);
            if(!deposito)
                return false;
            else
                return true;
        }
    }
    
    public boolean saca(double valor) {
        if((this.getSaldo() - valor) < (this.getMontanteMin()))
            throw new RuntimeException("Não foi possível completar o SAQUE!\nO novo valor do saldo não pode ser menor do que o MONTANTE MÍNIMO estipulado para a conta...\n*MONTANE MÍNIMO: " + String.format("%.2f", this.getMontanteMin()));
        else{
            boolean saque = super.saca(valor);
            if(!saque)
                return false;
            else
                return true;
        }
    }
    
    public void remunera() {
        if (this.getSaldo() < 0)
            throw new RuntimeException("Não foi possível realizar a REMUNERAÇÃO!\nEsta operação não pode ser aplicada em contas com saldo negativo...\n*SALDO ATUAL: " + String.format("%.2f", this.getSaldo()));
        else{
            double novosaldo = this.getSaldo()*1.02;
            this.setSaldo(novosaldo);
        }
    }
}