package model;

/**
 *
 * @author T-Gamer
 */
public class Pessoa {
    private String nome,cpf,senha;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public Pessoa(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Pessoa(String cpf) {
        this.cpf = cpf;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + '}';
    }
    
    
}
