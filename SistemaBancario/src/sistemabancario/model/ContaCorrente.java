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

    public void remunera() {
        if (this.getSaldo() < 0) throw new RuntimeException("Impossível realizar remuneração em saldo negativo!! Saldo: "+this.getSaldo());
        double novosaldo = this.getSaldo() * 1.01;
        this.setSaldo(novosaldo);
    }
    
    public boolean saca(double valor) {
        if((this.getSaldo() - valor) < (0 - this.getLimite())) {
            throw new RuntimeException("Impossivel realizar o saque! Valor ultrapassa o limite da conta!!!");
        }
        else{
            boolean saque = super.saca(valor);
            if(!saque) return false;
            else return true;
        }
    }
}