package sistemabancario.model;

public abstract class Conta implements ContaI {

    private long numConta;
    private Cliente cliente;
    private double saldo;

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

    @Override
    public Cliente getDono() {
        return cliente;
    }

    @Override
    public long getNumero() {
        return this.numConta;
    }

    @Override
    //sem implementação na classe pai
    public abstract void remunera();

    @Override
    //valor a ser depositado deve ser maior que 0
    public boolean deposita(double valor) {
        if (valor <= 0){
            return false;
        }
        else{
            this.setSaldo(this.getSaldo() + valor);
            return true;
        }
    }

    @Override
    //valor sacado deve ser maior que 0
    public boolean saca(double valor) {
        if (valor <= 0){
            return false;
        }
        else{
            this.setSaldo(this.getSaldo() - valor);
            return true;
        }
    }
}