package sistemabancario.model;

public class ContaCorrente extends Conta {

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
    
    public boolean saca(double valor) {
        if((this.getSaldo() - valor) < (0 - this.getLimite()))
            throw new RuntimeException("Não foi possível completar o SAQUE!\nO novo valor do saldo ultrapassa o limite da conta...\n*LIMITE: " + String.format("%.2f", this.getLimite()));
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
            double novosaldo = this.getSaldo() * 1.01;
            this.setSaldo(novosaldo);
        }
    }
}