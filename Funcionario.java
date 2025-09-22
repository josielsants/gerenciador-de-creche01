/**
 * Classe abstrata que representa um funcionário no sistema.
 * Herda de Pessoa e serve como base para Professor, Coordenador e Cuidador.
 * 
 * @author Sistema de Gestão Escolar
 * @version 1.0
 */
public abstract class Funcionario extends Pessoa{

    private String cargo;
    private String vinculo;
    private double salario;

    Funcionario(String id, String nome,int idade){
        super(id,nome,idade);
    }

    Funcionario(String id, String nome){
        super(id,nome);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}