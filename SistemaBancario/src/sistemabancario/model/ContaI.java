package sistemabancario.model;

public interface ContaI {
    
    public boolean deposita(double valor);
    public boolean saca(double valor);
    public Cliente getDono();
    public long getNumero();
    public double getSaldo();
    public void remunera();
    
}
