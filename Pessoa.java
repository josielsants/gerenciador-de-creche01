/**
 * Classe abstrata que representa uma pessoa no sistema.
 * Serve como base para Aluno, Funcionario e Responsavel.
 * 
 * @author Sistema de Gestão Escolar
 * @version 1.0
 */
public abstract class Pessoa {

    private String id;
    private String nome;
    protected int idade;
    private String cpf;

    Pessoa(String id, String nome, int idade){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    Pessoa(String id, String nome){
        this.id = id;
        this.nome = nome;
    }


    public void setIdade(int idade){
        this.idade = idade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (Utils.validarNome(nome)) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome inválido: " + nome);
        }
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (Utils.validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
    }


}
