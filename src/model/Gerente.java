package model;

/**
 *
 * @author uniebrunosilva
 */
public class Gerente extends Pessoa{
    public Gerente() {
    }

    public Gerente(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
    }

    public Gerente(String cpf, String senha) {
        super(cpf, senha);
    }

    public Gerente(String cpf) {
        super(cpf);
    }
}
