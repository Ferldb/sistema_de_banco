package sistemabancario.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Cliente implements Comparable<Cliente>{
    private long idcliente;
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private String endereco;
    private double salario;
    private int ordenar; //atributo para ordenação da lista de clientes

    public Cliente(long idcliente, String nome, String sobrenome, String rg, String cpf, String endereco, double salario) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
        this.salario = salario;
    }

    public long getId() {
        return idcliente;
    }

    public void setId(long id) {
        this.idcliente = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
     
    public String getSobrenome() {
        return sobrenome;
    }
    
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRg() {
        return rg;
    }
    
    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public int getOrdenar(){
        return ordenar;
    }
    
    public void setOrdenar(int o){
        this.ordenar = o;
    }
    
    @Override
    public String toString(){
        return this.getNome() + " " + this.getSobrenome() + " - " + this.getRg() + " " + this.getCpf() + " " + this.getEndereco();
    }

    @Override
    public int compareTo(Cliente c) {
        if(this.ordenar == 0){ //ordenar por nome
            return this.getNome().compareToIgnoreCase(c.getNome());
        }
        else if (this.ordenar == 1){ //ordenar por sobrenome
            return this.getSobrenome().compareToIgnoreCase(c.getSobrenome());
        }
        else { //ordenar por salario
            return this.getSalario() >= c.getSalario() ? -1 : 0;
        }
    }

}
