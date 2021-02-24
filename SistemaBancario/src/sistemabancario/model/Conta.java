package sistemabancario.model;

public class Conta implements ContaI {

    private long numConta;
    private Cliente cliente;
    private double saldo;
    private long tipoconta;

    public Conta(long idconta, Cliente cliente, double saldo) {
        this.numConta = idconta;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setNumero(long numConta) {
        this.numConta = numConta;
    }

    public void setDono(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public long getTipoconta() {
        return tipoconta;
    }

    public void setTipoconta(long tipoconta) {
        this.tipoconta = tipoconta;
    }

    @Override
    public Cliente getDono() {
        return cliente;
    }

    @Override
    public long getNumero() {
        return this.numConta;
    }

    @Override
    public void remunera() {
    }

    @Override
    public boolean deposita(double valor) {
        if (valor < 0) return false;
        this.setSaldo(this.getSaldo() + valor);
        return true;
    }

    @Override
    public boolean saca(double valor) {
        if (valor < 0) return false;
        else{
            this.setSaldo(this.getSaldo() - valor);
            return true;
        }
    }
}